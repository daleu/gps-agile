package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.domain.Entity;
import edu.upc.essi.gps.ecommerce.exceptions.NoHiHaTiquetException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Venda implements Entity {
    private int id;
    private List<LiniaVenda> liniesVenda;
    private String informacioTancar;
    private double preuPagament;
    private boolean finalitzada;
    private List<String> tiquet;

    public Venda(int numVenda) {
        this.id = numVenda;
        liniesVenda = new ArrayList<>();
        finalitzada = false;
        tiquet = null;
    }

    public int getId() {
        return id;
    }

    public void setPreuPagament(double valor) {preuPagament=valor;}
    public String getInformacioTancar() { return informacioTancar; }

    public boolean isEmpty() {
        return liniesVenda.isEmpty();
    }

    public boolean isFinalitzada() {return finalitzada;}

    public double getPreuTotal() {
        double total = 0.0;
        for (LiniaVenda lv : liniesVenda) {
            total += lv.getPreuTotal();
        }
        return total;
    }
    public double getCanvi() {
        return preuPagament-getPreuTotal();
    }

    public void tancar() {
        if (liniesVenda.isEmpty()) informacioTancar = "Venda anul·lada";
        else informacioTancar = "Venda finalitzada";
    }

    public void anular() {
        informacioTancar = "Venda anul·lada";
    }

    public void finalitzar() {
        finalitzada = true;
        calculaTiquet();
    }

    public void afegeixLinia(Producte p, Integer unitats) {
        boolean jahies = false;
        int i = 0;
        while (i<liniesVenda.size() && !jahies) {
            if (liniesVenda.get(i).getNomProducte().equals(p.getNom())) jahies = true;
            else ++i;
        }
        if (jahies) liniesVenda.get(i).incrementaQuantitat(unitats);
        else {
            LiniaVenda liniaVenda = new LiniaVenda(p,unitats);
            liniesVenda.add(liniaVenda);
        }
    }

    public int getNombreLiniesVenda() {
        return liniesVenda.size();
    }

    public LiniaVenda getLiniaVenda(int i) {
        return liniesVenda.get(i-1);
    }


    public boolean conteLiniaVenda(String codiBarres, int unitatsProd) {

        for(int i = 0; i < liniesVenda.size(); ++i) {
            if(liniesVenda.get(i).getCodiProducte() ==codiBarres) {
                if(liniesVenda.get(i).getQuantitat() >= unitatsProd) {
                    return true;
                }
            }
        }
        return false;
    }

    public void afegeixDevolucio(Producte pRetorn, int unitatsProd) {
        Producte retorn = new Producte(pRetorn.getNom(),pRetorn.getCodiBarres(),(-pRetorn.getPreuUnitat()));
        afegeixLinia(retorn,unitatsProd);
    }

    public void modificarLinia(String codiBarres, int unitatsProd) throws Exception {
        for (LiniaVenda lv: liniesVenda) {
            if(lv.getCodiProducte().equals(codiBarres)) {
                int dif = lv.getQuantitat() - unitatsProd;
                if (dif < 0) {
                    String s = "No es pot modificar la línia de venda";
                    throw new Exception(s);
                }
                else if(dif == 0) {
                    liniesVenda.remove(lv);
                }
                else {
                    lv.setQuantitat(lv.getQuantitat() - unitatsProd);
                }
            }
        }
    }
    public int getNumDiferentsIVAs() {
        List<Double> llistaIVAs = new ArrayList<>();
        for (LiniaVenda lv : liniesVenda) {
            if(!llistaIVAs.contains(lv.getIVAProducte())) llistaIVAs.add(lv.getIVAProducte());
        }
        return llistaIVAs.size();
    }
    public List<Double> getElsDiferentsIVAs(){
        List<Double> llistaIVAs = new ArrayList<>();
        for (LiniaVenda lv : liniesVenda) {
            if(!llistaIVAs.contains(lv.getIVAProducte())) llistaIVAs.add(lv.getIVAProducte());
        }
        return llistaIVAs;
    }
    public void calculaTiquet() {
        String sep = " | "; //Separacio
        tiquet = new ArrayList<>();
        tiquet.add(sep + "Tiquet" + sep); //El tiquet comenca a la posició 1 no a la 0
        tiquet.add(sep + "Num. Venda: " + id + sep);
        for (LiniaVenda lv : liniesVenda) {
            tiquet.add(sep + lv.getQuantitat() + sep + lv.getNomProducte() + sep + "P.u. " + new DecimalFormat("##.##").format(lv.getPreuUnitat()) + sep
                    + "P.l. " + new DecimalFormat("##.##").format(lv.getPreuTotal()) + sep);
        }
        List<Double> vIVAs = getElsDiferentsIVAs();
        for(int i = 0; i < vIVAs.size(); ++i) {
            tiquet.add(sep + vIVAs.get(i)*100 + "%" + sep + "P.B: " +
                    new DecimalFormat("##.##").format(getSumaPreuBaseVendaPerIva(vIVAs.get(i))) + sep
                    + "P.T: " + new DecimalFormat("##.##").format(getSumaPreuUnitatVendaPerIva(vIVAs.get(i))) + sep);
        }
        tiquet.add(sep + "Total: " + new DecimalFormat("##.##").format(getPreuTotal()) + sep + "Canvi: " +
                new DecimalFormat("##.##").format(getCanvi()) + sep);
    }

    public String getLiniaTiquet(int num) throws NoHiHaTiquetException {
        if (tiquet != null) {
            return tiquet.get(num);
        } else throw new NoHiHaTiquetException();
    }

    public double getSumaPreuBaseVendaPerIva(double iva) {
        double total = 0.0;
        for (LiniaVenda lv : liniesVenda) {
            total += lv.getTotalPreuBase(iva);
        }
        return total;
    }

    public double getSumaPreuUnitatVendaPerIva(double iva) {
        double total = 0.0;
        for (LiniaVenda lv : liniesVenda) {
            total += lv.getTotalUnitatBase(iva);
        }
        return total;
    }
}

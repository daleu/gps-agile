package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.domain.Entity;

import java.util.ArrayList;
import java.util.List;

public class Venda implements Entity {
    private int id;
    private List<LiniaVenda> liniesVenda;
    private String informacioTancar;
    private double preuPagament;
    private boolean finalitzada;

    public Venda(int numVenda) {
        this.id = numVenda;
        liniesVenda = new ArrayList<>();
        finalitzada = false;
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

    public double getTotal() {
        double total = 0.0;
        for (LiniaVenda lv : liniesVenda) {
            total += lv.getTotal();
        }
        return total;
    }
    public double getCanvi() {
        return preuPagament-getTotal();
    }

    public void tancar() {
        if (liniesVenda.isEmpty()) informacioTancar = "Venda anul·lada";
        else informacioTancar = "Venda finalitzada";
    }

    public void anular() {
        informacioTancar = "Venda anul·lada";
    }

    public void finalitzar() {finalitzada = true;}

    public void afegeixLinia(Producte p, Integer unitats) {
        LiniaVenda liniaVenda = new LiniaVenda(p,unitats);
        liniesVenda.add(liniaVenda);
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
            if(lv.getCodiProducte() == codiBarres) {
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

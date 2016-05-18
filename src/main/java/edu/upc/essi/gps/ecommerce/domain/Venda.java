package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.domain.Entity;
import edu.upc.essi.gps.ecommerce.exceptions.DevolucioNoPossibleException;
import edu.upc.essi.gps.ecommerce.exceptions.ModeDePagamentIncorrecteException;
import edu.upc.essi.gps.ecommerce.exceptions.NoHiHaTiquetException;
import edu.upc.essi.gps.ecommerce.exceptions.TarjetaNoValidaException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Venda implements Entity {
    static private String EFECTIU = "en efectiu";
    static private String TARJETA = "amb tarjeta";
    private int id;
    private List<LiniaVenda> liniesVenda;
    private String informacioTancar;
    private double preuPagament;
    private String tipusPagament;
    private boolean finalitzada;
    private String nomEmpleat;
    private String nomBotiga;
    private Calendar dataIHora;
    private Integer idTorn;
    Tiquet tiquet;

    public Venda(int numVenda) {
        this.id = numVenda;
        liniesVenda = new ArrayList<>();
        finalitzada = false;
        tiquet = null;
        nomBotiga = "BB";
        dataIHora = Calendar.getInstance();
        tipusPagament = EFECTIU;
    }

    public int getId() {
        return id;
    }

    public void setTipusPagamentEfectiu() {
        tipusPagament = EFECTIU;
    }

    public void setTipusPagamentTarjeta() {
        tipusPagament = TARJETA;
    }

    public void setPreuPagamentAmbEfectiu(double valor) throws ModeDePagamentIncorrecteException {
        if(tipusPagament.equals(EFECTIU)) preuPagament = valor;
        else throw new ModeDePagamentIncorrecteException();
    }

    public void setPagamentAmbTarjeta(double valor, String numTarjeta) throws ModeDePagamentIncorrecteException, TarjetaNoValidaException {
        if(tipusPagament.equals(TARJETA)) {
            if(numTarjeta.length() == 19 ) preuPagament = valor;
            else throw new TarjetaNoValidaException();
        }
        else throw new ModeDePagamentIncorrecteException();
    }

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

    public String getEmpleat() {
        return nomEmpleat;
    }

    public void setNomEmpleat(String nomPilaEmpleat) {
        this.nomEmpleat = nomPilaEmpleat;
    }

    public String getNomBotiga() {
        return nomBotiga;
    }

    //Una venda tindrà la mateixa botiga que el TPV que l'ha iniciada.
    public void setNomBotiga(String nomBotiga) { //NO CRIDAR DIRECTAMENT. Cridar tpvController.setNomBotigaVendaDefinitATPV();
        this.nomBotiga = nomBotiga;
    }

    public double getPreuDevolucio() {
        double preu = 0;
        for (LiniaVenda lv : liniesVenda) {
            if(lv.getPreuTotal() < 0) preu -= lv.getPreuTotal();
        }
        return preu;
    }

    public void setDataIHora(Calendar dataIHora) {
            this.dataIHora = dataIHora;
    }

    public void anular() {
        informacioTancar = "Venda anul·lada";
    }

    public void finalitzar(Torn tornActual) {
        finalitzada = true;
        informacioTancar = "Venda finalitzada";
        calculaTiquet(tornActual);
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
            if(Objects.equals(liniesVenda.get(i).getCodiProducte(), codiBarres)) {
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

    public void modificarLinia(String codiBarres, int unitatsProd) throws DevolucioNoPossibleException {
        for (LiniaVenda lv: liniesVenda) {
            if(lv.getCodiProducte().equals(codiBarres)) {
                int dif = lv.getQuantitat() - unitatsProd;
                if (dif < 0) {
                    throw new DevolucioNoPossibleException();
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
    public void calculaTiquet(Torn tornActual) {
        String sep = " | "; //Separacio
        tiquet = new Tiquet(id); //De moment els tiquets tenen el mateix id que la venda
        tiquet.addLinia(sep + "Tiquet" + sep); //El tiquet comenca a la posició 1 no a la 0
        //" | Nom empleat: Joan | Nom botiga: JJ | "
        tiquet.addLinia(sep + "Nom botiga: " + nomBotiga + sep);
        //" | Num. Venda: 1 | dd/mm/aaaa hh:mm | Codi Tiquet: 1"
        tiquet.addLinia(sep + "Num. Venda: " + id + sep + "Codi Tiquet: C" + tiquet.getNum());
        for (LiniaVenda lv : liniesVenda) {
            tiquet.addLinia(sep + lv.getQuantitat() + sep + lv.getNomProducte() + sep + "P.u. " + new DecimalFormat("##.##").format(lv.getPreuUnitat()) + sep
                    + "P.l. " + new DecimalFormat("##.##").format(lv.getPreuTotal()) + sep);
        }
        List<Double> vIVAs = getElsDiferentsIVAs();
        for(int i = 0; i < vIVAs.size(); ++i) {
            tiquet.addLinia(sep + vIVAs.get(i)*100 + "%" + sep + "P.B: " +
                    new DecimalFormat("##.##").format(getSumaPreuBaseVendaPerIva(vIVAs.get(i))) + sep
                    + "P.T: " + new DecimalFormat("##.##").format(getSumaPreuUnitatVendaPerIva(vIVAs.get(i))) + sep);
        }
        tiquet.addLinia(sep + "Total: " + new DecimalFormat("##.##").format(getPreuTotal()) + sep + "Canvi: " +
                new DecimalFormat("##.##").format(getCanvi()) + sep + "Pagat " + tipusPagament + sep);

        SimpleDateFormat  dF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataIH = dF.format(dataIHora.getTime());
        tiquet.addLinia(sep + dataIH + sep);
        tiquet.addLinia(sep + "Atès per: " + nomEmpleat + sep);
    }

    public String getLiniaTiquet(int num) throws NoHiHaTiquetException {
        if (tiquet != null) {
            return tiquet.getLinia(num);
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

    public void setIdTorn(int id){
        idTorn = id;
    }

    public Integer getIdTorn() {
        return idTorn;
    }
}

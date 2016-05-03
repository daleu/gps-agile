package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.ProducteNoExisteixException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaJaIniciadaException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaNoIniciadaException;
import edu.upc.essi.gps.ecommerce.repositoris.VendesRepositori;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;

/**
 * Created by edu on 28/04/16.
 */
public class TPV {
    //Classe singleton
    private static TPV instance;

    private Venda vendaActual;
    private double efectiuInici;
    private double efectiuFi;
    private double dinersEnCaixa;
    private Devolucio devolucioActual;
    private final DevolucionsServei devolucionsServei = new DevolucionsServei();
    private final VendesServei vendesServei = new VendesServei();

    private TPV(){}

    public static TPV getInstance() {
        if (instance == null) instance = new TPV();
        return instance;
    }

    //---------------------------
    // Estats de venda
    //---------------------------

    public void iniciarVenda() throws VendaJaIniciadaException {
       if (this.vendaActual == null) {
           vendaActual = vendesServei.novaVenda();
       } else {
           throw new VendaJaIniciadaException();
       }
    }

    public void iniciarVendaAmbID(int id) throws VendaJaIniciadaException { //Per a JOCS de PROVA
        if (this.vendaActual == null) {
            vendaActual = vendesServei.novaVendaAmbID(id);
        } else {
            throw new VendaJaIniciadaException();
        }
    }

    public void tancarVendaActual() throws VendaNoIniciadaException {
        if (vendaActual != null)  {
            vendesServei.guardarVenda(vendaActual);
            vendaActual.tancar();
            vendaActual = null;
        }
        else throw new VendaNoIniciadaException();
    }

    public Venda getVendaActual() {
        return vendaActual;
    }

    public void setVendaActual(Venda vendaActual) {
        this.vendaActual = vendaActual;
    }


    //------------------------------
    //INTRODUIR PRODUCTES A UNA VENDA
    //------------------------------

    public void passarCodi(String codiBarres) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerCodi(codiBarres);
        vendaActual.afegeixLinia(producteIdentificat,1);
    }

    public void introduirNomProducte(String nomProducte) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerNom(nomProducte);
        vendaActual.afegeixLinia(producteIdentificat,1);
    }

    public void afegirProducteLiniaVenda(String codiBarres, int unitats) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerCodi(codiBarres);
        if(producteIdentificat == null) throw new ProducteNoExisteixException();
        vendaActual.afegeixLinia(producteIdentificat,unitats);
    }

    public boolean possibilitatDeRetorn(int idVenda, String codiBarres, int unitatsProd) {
        Venda ven_anterior = vendesServei.trobaPerCodi(idVenda);
        if(ven_anterior != null) {
            ven_anterior.conteLiniaVenda(codiBarres,unitatsProd);
            return true;
        }
        return false;
    }

   //----------------------------------
    // SOBRE DEVOLUCIONS
    //----------------------------------

    public void iniciarDevolucio(){
        devolucioActual = devolucionsServei.novaDevolucio();
    }
    public void acabarDevolucio(){
        devolucioActual = devolucionsServei.novaDevolucio();
    }


    public void afegirDevolucioLiniaVenda(int idVenda, String codiBarres, int unitatsProd,String motiu) throws ProducteNoExisteixException, Exception {
       Producte pRetorn = Cataleg.getInstance().getProductePerCodi(codiBarres);
       //1. Introduit a linia de venda en negatiu
        vendaActual.afegeixDevolucio(pRetorn,unitatsProd);
        //2. Deixar constancia en la devolucio
        devolucioActual.setIdVenda(idVenda);
        devolucioActual.setCodiBarres(codiBarres);
        devolucioActual.setUnitatsProducte(unitatsProd);
        devolucioActual.setMotiu(motiu);
       //2. Actualitzar repositoris per evitar repetir
        devolucionsServei.guardarDevolucio(devolucioActual);
       vendesServei.indicarDevolucio(idVenda,codiBarres,unitatsProd);
    }

    //------------------------------
    // Afegir efectiu pel quadrament del torn
    //------------------------------

    public void setEfectiuInicial(double efectiu) {
        this.efectiuInici = efectiu;
    }

    public double getEfectiuInicial() {
        return this.efectiuInici;
    }

    public void setEfectiuFinal(double efectiu) {
        this.efectiuFi = efectiu;
    }

    public double getEfectiuFinal() {
        return this.efectiuFi;
    }

    public DevolucionsServei getdevolucionsServei() {
        return devolucionsServei;
    }

    public String obteMissatge() {
        return vendaActual.getInformacioTancar();
    }
}

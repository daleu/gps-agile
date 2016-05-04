package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.*;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;

/**
 * Created by edu on 28/04/16.
 */
public class TPVController {
    //Classe singleton
    private Venda vendaActual;
    private double efectiuInici;
    private double efectiuFi;
    private double dinersEnCaixa;
    private String estatQuadrament;
    private Devolucio devolucioActual;
    private final DevolucionsServei devolucionsServei = new DevolucionsServei();
    private final VendesServei vendesServei = new VendesServei();

    private double canvi;

    private Cataleg cataleg = new Cataleg();

    public TPVController(){}

    //---------------------------
    // Estats de venda
    //---------------------------

    public void iniciarVenda() throws VendaJaIniciadaException {
       if (this.vendaActual == null) {
           vendaActual = vendesServei.novaVenda();
       } else {
           if(this.vendaActual.isFinalitzada() ) vendaActual = vendesServei.novaVenda();
           else throw new VendaJaIniciadaException();
       }
    }

    public void iniciarVendaAmbID(int id) throws VendaJaIniciadaException { //Per a JOCS de PROVA
        if (this.vendaActual == null) {
            vendaActual = vendesServei.novaVendaAmbID(id);
        } else {
            throw new VendaJaIniciadaException();
        }
    }

    public void tancamentVenda() throws VendaNoIniciadaException, VendaJaFinalitzadaException {
        if (vendaActual != null)  {
            if(!vendaActual.isFinalitzada()) {
                vendesServei.guardarVenda(vendaActual);
                dinersEnCaixa += vendaActual.getPreuTotal();
                vendaActual.finalitzar();
            }
            else throw new VendaJaFinalitzadaException();
        }
        else throw new VendaNoIniciadaException();
    }

    public void tancamentVenda(double valor) throws VendaNoIniciadaException,PagamentInsuficientException {
        if (vendaActual != null)  {
            if(vendaActual.getPreuTotal()>valor) throw new PagamentInsuficientException();
            else {
                vendaActual.setPreuPagament(valor);
                canvi = vendaActual.getCanvi();
            }
            vendesServei.guardarVenda(vendaActual);
            dinersEnCaixa += vendaActual.getPreuTotal();
            vendaActual.tancar();
            vendaActual = null;
        }
        else throw new VendaNoIniciadaException();
    }

    public void anularVendaActual () {
        vendaActual.anular();
        vendaActual = null;
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
        Producte producteIdentificat = cataleg.getProductePerCodi(codiBarres);
        vendaActual.afegeixLinia(producteIdentificat,1);
    }

    public void introduirNomProducte(String nomProducte) throws ProducteNoExisteixException {
        Producte producteIdentificat = cataleg.getProductePerNom(nomProducte);
        vendaActual.afegeixLinia(producteIdentificat,1);
    }

    public void afegirProducteLiniaVenda(String codiBarres, int unitats) throws ProducteNoExisteixException {
        Producte producteIdentificat = cataleg.getProductePerCodi(codiBarres);
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
       Producte pRetorn = cataleg.getProductePerCodi(codiBarres);
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
        this.dinersEnCaixa = efectiu;
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

    //------------------------------
    // Càlcul del quadrament
    //------------------------------

    public void quadrament() {
        double diferencia = dinersEnCaixa - efectiuFi;
        if (Math.abs(diferencia) <= 5) { estatQuadrament = "Quadrament correcte"; }
        else { estatQuadrament = "Quadrament incorrecte"; }
    }

    public String getEstatQuadrament() {
        return estatQuadrament;
    }

    //------------------------------
    // Tancament Venda
    //------------------------------
    public void setPreuPagament(double valor) {vendaActual.setPreuPagament(valor);}
    public double getCanviUltimaVenda() {
        return vendaActual.getCanvi();
    }

    //------------------------------
    // Imprimir tiquet
    //------------------------------
    public String getLiniaTiquetVendaActual(int num) throws NoHiHaTiquetException {
        return vendaActual.getLiniaTiquet(num);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////FUNCIONS CONTROLADOR/////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean vendaActualIsEmpty() {
        return vendaActual.isEmpty();
    }

    public boolean vendaActualIsNull() {
        return vendaActual == null;
    }

    public double getTotalVenda() {
        return vendaActual.getPreuTotal();
    }

    public int getIdVenda() {
        return vendaActual.getId();
    }

    public void afegeixProducteACataleg(String nomProducte, String codiBarres, double preuUnitat) {
        Producte producte = new Producte(nomProducte,codiBarres,preuUnitat);
        cataleg.afegeixProducte(producte);
    }

    public String getCodiBarresDevolucio(int expectedIdVenda, String expectedCodiBarres, int i) {
        Devolucio dev = devolucionsServei.trobarPerParametres(expectedIdVenda,expectedCodiBarres,1);
        return dev.getCodiBarres();
    }

    public int getIdVendaDevolucio(int expectedIdVenda, String expectedCodiBarres, int i) {
        Devolucio dev = devolucionsServei.trobarPerParametres(expectedIdVenda,expectedCodiBarres,1);
        return dev.getIdVenda();
    }

    public String getMotiuDevolucio(int expectedIdVenda, String expectedCodiBarres, int i) {
        Devolucio dev = devolucionsServei.trobarPerParametres(expectedIdVenda,expectedCodiBarres,1);
        return dev.getMotiu();
    }
}

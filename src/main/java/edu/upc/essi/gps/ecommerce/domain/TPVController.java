package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.*;
import edu.upc.essi.gps.ecommerce.repositoris.TornServei;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

/**
 * Created by edu on 28/04/16.
 */
public class TPVController {
    //Classe singleton
    private Venda vendaActual;
    private Torn tornActual;
    private Quadrament quadrament;
    private Devolucio devolucioActual;
    private final DevolucionsServei devolucionsServei = new DevolucionsServei();
    private final VendesServei vendesServei = new VendesServei();
    private final TornServei tornServei = new TornServei();
    private String nomBotiga;
    private String screen;

    private Cataleg cataleg = new Cataleg();

    public TPVController(){}

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

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

    public void iniciarVendaAmbData(String data, String hora) throws VendaJaIniciadaException, ParseException {
        if (this.vendaActual == null) {
            vendaActual = vendesServei.novaVenda();
            vendaActual.setData(data,hora);
        } else {
            if(this.vendaActual.isFinalitzada() ) vendaActual = vendesServei.novaVenda();
            else throw new VendaJaIniciadaException();
        }
    }


    public void tancamentVenda() throws VendaNoIniciadaException, VendaJaFinalitzadaException, ParseException {
        if (vendaActual != null)  {
            if(!vendaActual.isFinalitzada()) {
                vendesServei.guardarVenda(vendaActual);
                if (tornActual != null) tornActual.incrementDinersEnCaixa(vendaActual.getPreuTotal());

                //TODO : If temporal fins quadrament arreglat
                if (tornActual == null){
                    tornActual = new Torn("BB","BB");
                    vendaActual.setData("10/05/2016","10:05");
                }
                vendaActual.finalitzar(tornActual);
            }
            else throw new VendaJaFinalitzadaException();
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
    public void setNomBotiga(String nomBotiga ) { this.nomBotiga = nomBotiga; }
    public String getNomBotiga() { return this.nomBotiga; }
    public void setNomBotigaVendaDefinitATPV(){ //Una venda tindrà la mateixa botiga que el TPV que l'ha iniciada.
        vendaActual.setNomBotiga(nomBotiga);
    }

    //------------------------------
    //INTRODUIR PRODUCTES A UNA VENDA
    //------------------------------

    public void afegirLiniaVendaPerCodi(int quantitat, String codiBarres) throws ProducteNoExisteixException {
        Producte producteIdentificat = cataleg.getProductePerCodi(codiBarres);
        vendaActual.afegeixLinia(producteIdentificat,quantitat);
    }

    public void afegirLiniaVendaPerNom(int quantitat, String nomProducte) throws ProducteNoExisteixException {
        Producte producteIdentificat = cataleg.getProductePerNom(nomProducte);
        vendaActual.afegeixLinia(producteIdentificat,quantitat);
    }

    public boolean possibilitatDeRetorn(int idVenda, String codiBarres, int unitatsProd) {
        Venda ven_anterior = vendesServei.trobaPerCodi(idVenda);
        if(ven_anterior != null) {
            if(ven_anterior.conteLiniaVenda(codiBarres,unitatsProd)) return true;
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
        //vendesServei.indicarDevolucio(idVenda,codiBarres,unitatsProd);
    }

    //------------------------------
    // Afegir efectiu pel quadrament del torn
    //------------------------------

    public void setEfectiuInicial(double efectiu) {
        if (tornActual != null && !tornActual.getFinalitzat()) {
            tornActual.setEfectiuInici(efectiu);
            tornActual.setDinersEnCaixa(efectiu);
        }
    }

    public double getEfectiuInicial() {
        if (tornActual != null && !tornActual.getFinalitzat()) { return this.tornActual.getEfectiuInici(); }
        return 0.0;
    }

    public void setEfectiuFinal(double efectiu) {
        if (tornActual != null && !tornActual.getFinalitzat()) {
            this.tornActual.setEfectiuFi(efectiu);
        }
    }

    public double getEfectiuFinal() {
        if (tornActual != null) { return this.tornActual.getEfectiuFi(); }
        return 0.0;
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

    public void quadrament(Double efectiu) {
        if (tornActual != null) {
            double diferencia = tornActual.getDinersEnCaixa() - efectiu;
            if (Math.abs(diferencia) <= 5) {
            //    screen = "Quadrament correcte";
                tornActual.setEfectiuFi(efectiu);
            }
            tornActual.setEfectiuFi(efectiu);

        }
    }

    //------------------------------
    // Tancament Venda
    //------------------------------
    public void setPreuPagament(double valor) {vendaActual.setPreuPagament(valor);}
    public double getCanviVenda() {
        return vendaActual.getCanvi();
    }

    //------------------------------
    // Imprimir tiquet
    //------------------------------
    public String getLiniaTiquetVendaActual(int num) throws NoHiHaTiquetException {
        return vendaActual.getLiniaTiquet(num);
    }

    public String getDataActual() {
        Calendar calendari = Calendar.getInstance();
        return ""+calendari.get(Calendar.DATE) + "/" + calendari.get(Calendar.DAY_OF_MONTH) + "/" + calendari.get(Calendar.YEAR);
    }
    public String getHoraActual() {
        Calendar calendari = Calendar.getInstance();
        return "" + calendari.get(Calendar.HOUR) + ":" + calendari.get(Calendar.MINUTE);
    }

    public String getDataIHoraActual() {
        return getDataActual() + " " + getHoraActual();
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

    public void afegeixProducteACatalegAmbPreuUnitat(String nomProducte, String codiBarres, double preuUnitat, double iva) {
        Producte producte = new Producte(nomProducte,codiBarres,preuUnitat,iva); //Preu Base calculat
        cataleg.afegeixProducte(producte);
    }

    public double getPreuBaseProducte(String nomProducte) {
        return cataleg.getPreuBaseProducte(nomProducte);
    }

    public double getPreuUnitatProducte(String nomProducte) {
        return cataleg.getPreuUnitatProducte(nomProducte);
    }


    public double getSumaPreuBaseVendaPerIva(double iva) {
        double aux =  vendaActual.getSumaPreuBaseVendaPerIva(iva);
        return aux;
    }

    public double getSumaPreuUnitatVendaPerIva(double iva) {
        double aux =  vendaActual.getSumaPreuUnitatVendaPerIva(iva);
        return aux;
    }

    public void guardarVendaActual() {
        vendesServei.guardarVenda(vendaActual);
        vendaActual = null;
    }

    public void introduirDevolucio(int idVenda, String codiProd, int unitats, String motiu) throws ProducteNoExisteixException, Exception {
        iniciarDevolucio();
        if(possibilitatDeRetorn(idVenda,codiProd,unitats)) {
            afegirDevolucioLiniaVenda(idVenda, codiProd, unitats, motiu);
        }
        else throw new DevolucioNoPossibleException();
    }

    public void introduirVendaJaAcabada(int idVenda, Map<String,Integer> productesVenda) throws VendaJaIniciadaException, ProducteNoExisteixException {
        iniciarVendaAmbID(idVenda);
        Set<String> prods = productesVenda.keySet();
        for(String p: prods) {
            afegirLiniaVendaPerCodi(productesVenda.get(p), p);
        }
        guardarVendaActual();
    }


    public void iniciarTorn(String nomEmpleat) {
        if (tornActual == null) {
            tornActual = new Torn(nomEmpleat);
            tornActual.setNomBotiga(nomBotiga);
            screen = "Bon dia, l'atén en " + nomEmpleat;
        }
        else { screen = "Ja hi ha un torn iniciat"; }
    }

    public Torn getTornActual() {
        return tornActual;
    }

    public void finalitzaTorn() {
        tornActual.finalitza();
    }

    public void cancelaAccioTorn() {
        tornActual.cancelarFinalitzacio();
        screen = "Cancel·lacio acceptada";
    }
}

package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.*;
import edu.upc.essi.gps.ecommerce.repositoris.TornServei;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
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
    private Devolucio devolucioActual;
    private final DevolucionsServei devolucionsServei = new DevolucionsServei();
    private final VendesServei vendesServei = new VendesServei();
    private final TornServei tornServei = new TornServei();
    private String nomBotiga;
    private String screen;
    private Calendar dataIHora;

    private ArrayList<String> liniesQuadrament;
    private int numVendesQuadrament;

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
           if(this.tornActual != null) vendaActual.setIdTorn(tornActual.getId());
           vendaActual.setNomBotiga(nomBotiga);
       } else {
           if(this.vendaActual.isFinalitzada() ) {
               vendaActual = vendesServei.novaVenda();
               if(this.tornActual != null) vendaActual.setIdTorn(tornActual.getId());
               vendaActual.setNomBotiga(nomBotiga);
           }
           else throw new VendaJaIniciadaException();
       }
    }

    public void iniciarVendaAmbID(int id) throws VendaJaIniciadaException { //Per a JOCS de PROVA
        if (this.vendaActual == null) {
            vendaActual = vendesServei.novaVendaAmbID(id);
            if (tornActual!=null)vendaActual.setIdTorn(tornActual.getId());
        } else {
            throw new VendaJaIniciadaException();
        }
    }

    public void tancamentVenda() throws VendaNoIniciadaException, VendaJaFinalitzadaException, ParseException {
        if (vendaActual != null)  {
            if(!vendaActual.isFinalitzada()) {
                vendesServei.guardarVenda(vendaActual);

                if (tornActual != null) tornActual.incrementDinersEnCaixa(vendaActual.getPreuTotal());

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
        else {
            Torn t = tornServei.getUltimTorn();
            return t.getEfectiuFi();
        }
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

    public void setTipusPagamentEfectiu() {
        vendaActual.setTipusPagamentEfectiu();
    }
    public void setTipusPagamentTarjeta() {
        vendaActual.setTipusPagamentTarjeta();
    }
    public void setPreuPagamentAmbEfectiu(double valor) throws ModeDePagamentIncorrecteException {
        vendaActual.setPreuPagamentAmbEfectiu(valor);
    }
    public void pagamentAmbTarjeta(double valor, String numTarjeta) throws ModeDePagamentIncorrecteException, TarjetaNoValidaException {
        vendaActual.setPagamentAmbTarjeta(valor, numTarjeta);
    }
    public double getCanviVenda() {
        return vendaActual.getCanvi();
    }

    //------------------------------
    // Imprimir tiquet
    //------------------------------
    public String getLiniaTiquetVendaActual(int num) throws NoHiHaTiquetException {
        return vendaActual.getLiniaTiquet(num);
    }

    public void setDataIHora(String dataIHora) throws ParseException {
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dataIHora = Calendar.getInstance();
        this.dataIHora.setTime(dF.parse(dataIHora));
        if(vendaActual != null) vendaActual.setDataIHora(this.dataIHora);
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

            if(motiu == "") motiu = "No existeix motiu";

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
            int id = tornServei.assignarIdTorn();
            tornActual = new Torn(id,nomEmpleat,nomBotiga);
            screen = "Bon dia, l'atén en " + nomEmpleat;
        }
        else { screen = "Ja hi ha un torn iniciat"; }
    }

    public Torn getTornActual() {
        return tornActual;
    }

    public void finalitzaTorn() {
        tornActual.finalitza();
        tornServei.guardarTorn(tornActual);
        tornActual = null;
    }

    public void cancelaAccioTorn()
    {
        if (tornActual == null) tornActual = tornServei.getUltimTorn();
        else tornActual = null;
        screen = "Cancel·lacio acceptada";
    }

    public void finalitzaTorn(Double efectiu) {
        tornActual.setEfectiuFi(efectiu);
        tornActual.finalitza();
        tornServei.guardarTorn(tornActual);
    }

    public void calcularQuadraments() {
        List<Torn> listQuadraments = tornServei.llistarTorns();

        liniesQuadrament = new ArrayList<String>();

        for (int i = 0; i<listQuadraments.size(); ++i){

            Torn tornAux = listQuadraments.get(i);
            List<Venda> listVendes = vendesServei.llistarVendes();
            ArrayList vendes = new ArrayList<Integer>();

            for (int j = 0; j < listVendes.size(); ++j){
                Venda vendaAux =  listVendes.get(j);
                if(vendaAux.getIdTorn() != null){
                    if (vendaAux.getIdTorn() == tornAux.getId()) vendes.add(vendaAux.getId());
                }

            }
            numVendesQuadrament = vendes.size();

            String linea = "TORN " + tornAux.getId() + ":  EfectiuInicial: "+tornAux.getEfectiuInici().toString()+" | EfectiuFinal: "+tornAux.getEfectiuFi().toString()+" | Numero de Vendes: "+vendes.size()+" | Vendes: ";
            for (int x = 0; x < vendes.size(); ++x){
                if(x == 0) linea = linea + vendes.get(x);
                else linea = linea +","+vendes.get(x);
            }

            liniesQuadrament.add(linea);
        }
    }

    public int getNumVendesQUadrament() {
        return numVendesQuadrament;
    }
    public Devolucio getUltimaDevolucio() {
        return devolucionsServei.getUltimaDevolucio();
    }

    public void setTornActual(Torn tornActual) {
        this.tornActual = tornActual;
    }

    public String getLiniaQuadrament(int numLinia) {
        return liniesQuadrament.get(numLinia-1);
    }

    public int getNumLineasQuadrament() {
        return liniesQuadrament.size();
    }
}

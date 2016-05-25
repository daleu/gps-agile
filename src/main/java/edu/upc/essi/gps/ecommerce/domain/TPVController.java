package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.domain.descomptes.Descompte;
import edu.upc.essi.gps.ecommerce.domain.descomptes.FactoriaDescomptes;
import edu.upc.essi.gps.ecommerce.exceptions.*;
import edu.upc.essi.gps.ecommerce.repositoris.TornServei;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    private ArrayList<String> liniesDesquadrament;
    private int numVendesQuadrament;

    private Cataleg cataleg = new Cataleg();

    public TPVController(){
        FactoriaDescomptes.startFactory();
    }

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

    public void iniciarVendaAmbID(int id) throws VendaJaIniciadaException {
        if (this.vendaActual == null) {
            vendaActual = vendesServei.novaVendaAmbID(id);
            if(this.tornActual != null) vendaActual.setIdTorn(tornActual.getId());
            vendaActual.setNomBotiga(nomBotiga);
        } else {
            if(this.vendaActual.isFinalitzada() ) {
                vendaActual = vendesServei.novaVendaAmbID(id);
                if(this.tornActual != null) vendaActual.setIdTorn(tornActual.getId());
                vendaActual.setNomBotiga(nomBotiga);
            }
            else throw new VendaJaIniciadaException();
        }
    }

    public void tancamentVenda() throws VendaNoIniciadaException, VendaJaFinalitzadaException, ParseException {
        if (vendaActual != null)  {
            if(!vendaActual.isFinalitzada()) {

                vendesServei.guardarVenda(vendaActual);

                if (tornActual != null && (vendaActual.getTipusPagament() != "amb tarjeta") ) {
                    tornActual.incrementDinersEnCaixa(vendaActual.getPreuTotal());
                }

                vendaActual.gestionarDevolucions(devolucionsServei);

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

    public void acabarDevolucio(){
        devolucioActual = devolucionsServei.novaDevolucio();
    }




    //------------------------------
    // Afegir efectiu pel quadrament del torn
    //------------------------------

    public void setEfectiuInicial(double efectiu) {
        if (tornActual != null) {
            tornActual.setEfectiuInici(efectiu);
            tornActual.setDinersEnCaixa(efectiu);
        }
    }

    public double getEfectiuInicial() {
        if (tornActual != null) { return this.tornActual.getEfectiuInici(); }
        return 0.0;
    }

    public double getEfectiuFinal() {
        //System.out.println(tornServei.getUltimTorn().getEfectiuFi());
        return tornServei.getUltimTorn().getEfectiuFi();
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
        Torn t = tornServei.getUltimTorn();
        t.setEfectiuTemporal(efectiu);
        double diferencia = t.getDinersEnCaixa() - efectiu;
        //System.out.println(t.getDinersEnCaixa());
        if (Math.abs(diferencia) == 0) {
            screen = "Torn finalitzat amb quadrament de caixa";
            t.setQuadrat(true);
            t.setEfectiuFi(efectiu);
        }
        else if (Math.abs(diferencia) >= 5) {  //més efectiu en caixa del real
            screen = "Torn no finalitzat. La diferència és major o igual a 5 euros entre l'efectiu en caixa introduit i el suposat";
            tornActual = t;
            tornServei.eliminarTorn(tornActual);
        }
        else if (Math.abs(diferencia) < 5) {  //menys efectiu en caixa del real
            screen = "Torn no finalitzat. La diferència és menor a 5 euros entre l'efectiu en caixa introduit i el suposat";
            tornActual = t;
            tornServei.eliminarTorn(tornActual);
        }
    }

    public void acceptoDesquadrament() {    //el torn no està finalitzat
        tornActual.setEfectiuFi(tornActual.getEfectiuTemporal());
        screen = "Torn finalitzat amb desquadrament de caixa";
        tornActual.setQuadrat(false);
        finalitzaTorn();
    }

    //------------------------------
    // Tancament Venda
    //------------------------------
    public void setPreuPagamentAmbEfectiu(double valor) throws ModeDePagamentIncorrecteException {
        vendaActual.setPreuPagamentAmbEfectiu(valor);
    }
    public void setPreuPagamentAmbTarjeta(double valor) throws ModeDePagamentIncorrecteException {
        vendaActual.setPreuPagamentAmbTarjeta(valor);
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
        if (vendaActual != null) vendaActual.setDataIHora(this.dataIHora);
        if (tornActual != null) tornActual.setDataIHoraFiTorn(this.dataIHora);
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

    public void afegirDevolucioAVenda(int idVenda, String codiBarres, int unitatsProd,String motiu) throws ProducteNoExisteixException{

        Producte pRetorn = cataleg.getProductePerCodi(codiBarres);
        if (pRetorn != null) {
            if (possibilitatDeRetorn(idVenda,codiBarres,unitatsProd)) {
                devolucioActual = devolucionsServei.novaDevolucio(pRetorn, unitatsProd, motiu, idVenda, vendaActual.getId());
                vendaActual.afegeixDevolucio(devolucioActual);
            }
            else screen = "El producte introduit no es pot retornar";
        }
        else {
            throw new ProducteNoExisteixException();
        }
    }

    public int getIdVendaDevolucio(int expectedIdVenda, String expectedCodiBarres, int i) {
        Devolucio dev = devolucionsServei.trobarPerParametres(expectedIdVenda,expectedCodiBarres,1);
        return dev.getIdVendaCompra();
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
        return vendaActual.getSumaPreuBaseVendaPerIva(iva);
    }

    public double getSumaPreuUnitatVendaPerIva(double iva) {

        return vendaActual.getSumaPreuUnitatVendaPerIva(iva);
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
        tornServei.guardarTorn(tornActual);
        tornActual = null;
    }

    public void cancelaAccioTorn()
    {
        if (tornActual != null) {   //torn iniciat per error
            tornActual = null;
            screen = "Cancel·lacio acceptada. No hi ha cap torn iniciat";
        }
        else {  //torn finalitzat per error
            tornActual = tornServei.getUltimTorn();
            tornServei.eliminarTorn(tornActual);
            screen = "Cancel·lacio acceptada. En " + tornActual.getNomEmpleat() + " continua amb el seu torn";
        }
    }

    public void calcularDesquadraments() {
        List<Torn> listTorns = tornServei.llistarTorns();
        //System.out.println(listTorns.size());
        //System.out.println(tornServei.llistarTorns());
        liniesDesquadrament = new ArrayList<String>();

        for (int i = 0; i<listTorns.size(); ++i){
            Torn tornAux = listTorns.get(i);
            if (!tornAux.getQuadrat()) {
                String linea = "TORN " + tornAux.getId() + ": NomEmpleat: " + tornAux.getNomEmpleat() + " | Botiga: " + tornAux.getNomBotiga() +
                        " | Data i Hora: " + tornAux.getDataIHoraFiTorn() + " | EfectiuInicial: " + tornAux.getEfectiuInici().toString() +
                        " | EfectiuFinal: " + tornAux.getEfectiuFi().toString() + " | Diferencia: " +
                        (tornAux.getEfectiuFi() - tornAux.getDinersEnCaixa());
                liniesDesquadrament.add(linea);
            }
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
        return liniesDesquadrament.get(numLinia-1);
    }

    public int getNumLineasQuadrament() {
        return liniesDesquadrament.size();
    }

    public boolean existsLiniaTiquet(List<String> list) {
        return vendaActual.getTiquet().existsLinies(list);
    }

    public int getIdVendaRetorn(int idVendaCompra, String codiProd, int i) {
        Devolucio dev = devolucionsServei.trobarPerParametres(idVendaCompra,codiProd,1);
        return dev.getIdVendaRetorn();
    }

    public int getNumDevolucionsVenda(int idVenda) {
        Venda v = vendesServei.trobaPerCodi(idVenda);
        return v.getNumDevolucions();
    }

    public List<String> imprimirLListaDescomptes() {
        return FactoriaDescomptes.getLlistaDescomptes();
    }

    public Descompte getDescompteImportByCodi(String codiDeBarres) {
        return FactoriaDescomptes.getDescompteImportByCodi(codiDeBarres);
    }

    public Descompte getDescomptePercentatgeByImport(String codiDeBarres) {
        return FactoriaDescomptes.getDescomptePercentatgeByCodi(codiDeBarres);
    }
}

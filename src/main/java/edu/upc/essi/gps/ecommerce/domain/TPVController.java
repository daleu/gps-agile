package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.domain.descomptes.Descompte;
import edu.upc.essi.gps.ecommerce.domain.descomptes.DescompteImport;
import edu.upc.essi.gps.ecommerce.domain.descomptes.DescomptePercentatge;
import edu.upc.essi.gps.ecommerce.domain.ofertes.Oferta;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaNxM;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaPercentatge;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaRegal;
import edu.upc.essi.gps.ecommerce.exceptions.*;
import edu.upc.essi.gps.ecommerce.repositoris.DescomptesServei;
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
    private final DescomptesServei descomptesServei = new DescomptesServei();
    private final TornServei tornServei = new TornServei();
    private String nomBotiga;
    private String screen;
    private Calendar dataIHora;

    private ArrayList<String> liniesDesquadrament;
    private List<String> llistaDescomptes = null;
    private int numVendesQuadrament;

    private Cataleg cataleg = new Cataleg();

    public TPVController(){
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

                if(vendaActual.PreuTotalNegatiuTargeta()){
                    screen = "Error: El resultat de la venda es negatiu, i hauria de ser positiu";
                }

                if (tornActual != null && (vendaActual.getTipusPagament() != "amb tarjeta") ) {
                    tornActual.incrementDinersEnCaixa(vendaActual.getPreuTotal());
                }

                vendaActual.gestionarDevolucions(devolucionsServei);
                if(!vendaActual.existeixenOfertes()){
                    vendaActual.finalitzar(tornActual);
                    vendesServei.guardarVenda(vendaActual);
                }
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
        if (ven_anterior == null) {
            if (vendaActual.getId() == idVenda) {ven_anterior = vendaActual; }
        }
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

    public void imprimirLListaDescomptes() {
        llistaDescomptes = descomptesServei.imprimirLlistaDescomptes();
    }

    public void nouDescomptePerImport(String descompte, String dataCaducitat, String impMinim) {
        SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dF.parse(dataCaducitat);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            descomptesServei.nouDescompte(Double.parseDouble(descompte),calendar,"Import",Double.parseDouble(impMinim));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public Descompte getDescompteImportByCodi(String codiDeBarres) {
        return descomptesServei.trobaPerCodi(Integer.parseInt(codiDeBarres));
    }

    public void nouDescomptePerPercentatge(Double key, Calendar calendar) {
        descomptesServei.nouDescompte(key,calendar,"Percentatge",null);
    }

    public Descompte getDescomptePercentatgeByCodi(String codiDeBarres) {
        return descomptesServei.trobaPerCodi(Integer.parseInt(codiDeBarres));
    }

    public String getLineaDescompte(int numLinia) {
        return llistaDescomptes.get(numLinia-1);
    }

    public void introduirDescompteVendaActual(int idDesc) {

        Descompte desc = descomptesServei.trobaPerCodi(idDesc);

        if(desc == null) {
            screen = "Descompte no existeix";return;
        }

        if(vendaActual.isEmpty()) {
            screen = "El val no es pot usar en una venda buida";
            return;
        }

        String tipus="";
        if (desc instanceof DescompteImport) tipus = "Import";
        else if (desc instanceof DescomptePercentatge) tipus = "Percentatge";

        if (possibilitatDeDescompte(idDesc,desc,tipus)){
            vendaActual.afegirDescompte(desc);
        }
    }

    private boolean possibilitatDeDescompte(int idDesc, Descompte desc, String tipus) {

        //1. Mirar si no esta caducat
        if (dataIHora.compareTo(desc.getDataCaducitat()) <= 0){ // dataIhora <= dataCaducitat

            //2. Mirar condicions
            switch (tipus) {
                case "Import":
                    DescompteImport dAux = (DescompteImport) desc;
                    if (vendaActual.getPreuADividir() >= dAux.getImportMinim()) {
                        screen = "Possible aplicar descompte";
                        return true;
                    }
                    else {
                        screen = "Import mínim no vàlid";
                        return false;
                    }
            }
            screen = "Possible aplicar descompte";
            return true;
        }
        else screen = "Descompte caducat";
        return false;
    }

    public void nouDescomptePerPercentatgeAmbID(int id,Double descompte, Calendar dataCad) {


        descomptesServei.nouDescompte(id,descompte,dataCad,"Percentatge",null);


    }

    public void nouDescomptePerImportAmbID(String id, String descompte, String importMinim, String dataCad) throws ParseException {
        SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dF.parse(dataCad);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        descomptesServei.nouDescompte(Integer.parseInt(id),Double.parseDouble(descompte),calendar,"Import",Double.parseDouble(importMinim));

    }

    public void treureDescompteVendaActual(int idDescompte) {
        Descompte descompte = descomptesServei.trobaPerCodi(idDescompte);
        if (descompte != null) {
            vendaActual.treureDescompte(descompte);
        }
        else screen = "Descompte no existeix";
    }

    public double getRetornDevolucioVenda() { return vendaActual.getRetornDevolucio(); }

    //-----------------------------------
    // Ofertes
    //-----------------------------------

    public void afegirOfertaNxMAProducte(int id, int N, int M, Calendar calendarInici, Calendar calendarFinal, String idProducte) throws ProducteNoExisteixException {
        String[] productes = idProducte.split(",");
        for (int i = 0; i < productes.length; i++) {
                cataleg.getProductePerCodi(productes[i]).afegirOfertaNxM(id, N, M, calendarInici, calendarFinal);
        }
    }

    public boolean existeixOfertaAlProducte(int id, String idProducte) throws ProducteNoExisteixException {
        return (cataleg.getProductePerCodi(idProducte).existeixOferta(id));
    }

    public void afegirOfertaPercentatgeAProducte(int id, int percentatge, Calendar calendarInici, Calendar calendarFinal, String idProducte) throws ProducteNoExisteixException {
        String[] productes = idProducte.split(",");
        for (int i = 0; i < productes.length; i++) {
            cataleg.getProductePerCodi(productes[i]).afegirOfertaPercentatge(id, percentatge, calendarInici, calendarFinal);
        }
    }

    public void afegirOfertaRegalAProducte(int id, String quantitat, String idRegal, Calendar calendarInici, Calendar calendarFinal, String idProducte) throws ProducteNoExisteixException {
        String[] productes = idProducte.split(",");
        String[] regals = idRegal.split(",");
        StringBuilder nomRegals = new StringBuilder();
        for (int j = 0; j < regals.length; j++) {
            nomRegals.append(cataleg.getProductePerCodi(regals[j]).getNom());
            if (j+1 < regals.length) nomRegals.append(",");
        }
        for (int i = 0; i < productes.length; i++) {
            cataleg.getProductePerCodi(productes[i]).afegirOfertaRegal(id, quantitat, nomRegals.toString(), calendarInici, calendarFinal);
        }
    }


    public void llistarOfertesPerProducte() throws ProducteNoExisteixException {
        ArrayList<Producte> productesPerNom = cataleg.getAllProductesPerNom();
        ListIterator<Producte> index = productesPerNom.listIterator();
        StringBuilder llista = new StringBuilder();
        Producte prod;
        while (index.hasNext()) {
            prod = index.next();
            llista.append(prod.getOfertesProducte());
            if (index.hasNext()) llista.append("| ");
        }
        screen = llista.toString();
    }

    public String getMiisatgeOferta(int numMissatges) {
        return vendaActual.getMissatgeOferta(numMissatges);
    }

    public int getNumMissatgesOferta() {
        return vendaActual.getNumMissatgesOferta();
    }

    public void aplicarOferta(String nomP) {
        vendaActual.aplicarOfertaSobreProducte(nomP);
    }

    public void noAplicarOferta(String nomP) {
        vendaActual.noAplicarOfertaSobreProducte(nomP);
    }
}

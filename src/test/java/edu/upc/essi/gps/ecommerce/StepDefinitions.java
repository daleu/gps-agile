package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;
import edu.upc.essi.gps.ecommerce.domain.*;
import edu.upc.essi.gps.ecommerce.exceptions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StepDefinitions {
    /*
     Enter (\\d+)
     Double (.+)
     String \"([^\"]*)\"
      */

    private TPVController tpvController = new TPVController();
    private Exception exception;

    @Quan("^inicio una venda$")
    public void iniciarVenda() {
        try {
            tpvController.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^la venda no te linies de venda$")
    public void laVendaNoTeLiniesDeVenda() throws Throwable {
        assertEquals(true, tpvController.vendaActualIsEmpty());
    }

    @Aleshores("^el preu total de la venda es (.+)$")
    public void elPreuTotalDeLaVendaEs(double totalExpected) throws Throwable {
        assertEquals(totalExpected, tpvController.getTotalVenda(), 0.001);
    }

    @Aleshores("^la venda te per identificador (\\d+)$")
    public void laVendaTePerIdentificador(int expectedId) throws Throwable {
        assertNotNull(tpvController.getVendaActual());
        assertEquals(expectedId, tpvController.getIdVenda());
    }

    @Donat("^que hi ha una venda iniciada$")
    public void queHiHaUnaVendaIniciada() throws Throwable {
        try {
            tpvController.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^obtinc un error que diu \"([^\"]*)\"$")
    public void obtincUnErrorQueFDiu(String expectedMessage) throws Throwable {
        assertNotNull(this.exception);
        assertEquals(expectedMessage, this.exception.getMessage());
    }

    //TODO Decidir si fa falta aquesta funcio, ja que si la venda no es crea no cal ficarla a null
    @Donat("^que no hi ha cap venda iniciada$")
    public void queNoHiHaCapVendaIniciada() throws Throwable {
        tpvController.setVendaActual(null);
    }

    @I("^existeix el producte \"([^\"]*)\" amb codi de barres \"([^\"]*)\" i preu per unitat (.+)$")
    public void existeixElProducteAmbCodiDeBarresIPreuPerUnitat(String nomProducte, String codiBarres, double preuUnitat) throws Throwable {
        tpvController.afegeixProducteACataleg(nomProducte, codiBarres, preuUnitat);
    }

    //TODO: Es podria modificar per la venda te (int) linies de venda. FUNCIO A SOTA
    @Aleshores("^la venda te una linia de venda$")
    public void laVendaTeUnaLiniaDeVenda() throws Throwable {
        assertEquals(1, tpvController.getVendaActual().getNombreLiniesVenda());
    }

    @Aleshores("^la venda te (\\d+) linia de venda$")
    public void laVendaTeLiniaDeVenda(int num) throws Throwable {
        assertEquals(num, tpvController.getVendaActual().getNombreLiniesVenda());
    }


    @I("^la linia de venda (\\d+) te per producte \"([^\"]*)\"$")
    public void laLiniaDeVendaTePerProducte(int i, String expectedNom) throws Throwable {
        assertEquals(expectedNom, tpvController.getVendaActual().getLiniaVenda(i).getNomProducte());
    }

    @I("^la linia de venda (\\d+) te per preu unitat (.+)$")
    public void laLiniaDeVendaTePerPreuUnitat(int i, double expectedPreu) throws Throwable {
        assertEquals(expectedPreu, tpvController.getVendaActual().getLiniaVenda(i).getPreuUnitat(), 0.001);
    }

    @I("^la linia de venda (\\d+) te per quantitat (\\d+)$")
    public void laLiniaDeVendaTePerQuantitat(int i, int expectedQuantitat) throws Throwable {
        assertEquals(expectedQuantitat, tpvController.getVendaActual().getLiniaVenda(i).getQuantitat());
    }

    @I("^es finalitza la venda$")
    public void esFinalitzaLaVenda() throws Throwable {
        try {
            tpvController.tancamentVenda();
        } catch (VendaNoIniciadaException | VendaJaFinalitzadaException e) {
            this.exception = e;
        }
    }

    @Quan("^s'anula la venda$")
    public void sAnulaLaVenda() throws Throwable {
        tpvController.anularVendaActual();
    }


    @I("^s'afegeix a la linia de venda (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void sAfegeixALaLiniaDeVendaUnitatsDelProducteAmbCodiDeBarres(int unitatsProd, String codiBarres) throws Throwable {
        tpvController.afegirLiniaVendaPerCodi(unitatsProd, codiBarres);
    }

    @Quan("^introdueixo al tpv (.+)  d'efectiu inicial$")
    public void introduirEfectiuInicial(double efectiu) throws Throwable {
        tpvController.setEfectiuInicial(efectiu);
    }

    @Aleshores("^el tpv té (.+)  d'efectiu inicial$")
    public void elTpvTeInicials(double inicial) throws Throwable {
        assertEquals(inicial, tpvController.getEfectiuInicial(), 0.0);
    }

    @Quan("^introdueixo al tpv (.+) d'efectiu final per fer el quadrament")
    public void introduirEfectiuFinal(double efectiu) throws Throwable {
        tpvController.quadrament(efectiu);
    }

    @Aleshores("^el tpv té (.+) d'efectiu final$")
    public void elTpvTeFinals(double fin) throws Throwable {
        assertEquals(fin, tpvController.getEfectiuFinal(), 0.0);
    }

    @Aleshores("^obtinc un missatge que diu \"([^\"]*)\"$")
    public void obtenirMissatge(String msg) {
        assertEquals(msg, tpvController.getScreen());
    }

    @Donat("^que hi ha al tpv (.+) d'efectiu inicial")
    public void elTpvHiHaInicials(double efectiu) throws Throwable {
        tpvController.setEfectiuInicial(efectiu);
    }

    @I("^que hi ha una linia de venda amb (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void HiHaUnaLiniaDeVenda(int unitats, String codi) throws Throwable {
        tpvController.afegirLiniaVendaPerCodi(unitats, codi);
    }


    @Aleshores("^existeix una devolucio del producte \"([^\"]*)\" de la venda (\\d+)")
    public void existeixUnaDevolucioDelProducteDeLaVendaPelMotiu(String expectedCodiBarres, int expectedIdVenda) {
        assertEquals(expectedIdVenda, tpvController.getIdVendaDevolucio(expectedIdVenda, expectedCodiBarres, 1));

    }

    @Quan("^inicio una nova venda$")
    public void inicio_una_nova_venda() throws Throwable {
        try {
            tpvController.iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }


    @Aleshores("^no hi ha cap venda iniciada$")
    public void noHiHaCapVendaIniciada() throws Throwable {
        tpvController.vendaActualIsNull();
    }


    @Aleshores("^el valor a retornar al client és de (.+)$")
    public void elValorARetornarAlClientÉsDe(double valor) throws Throwable {
        assertEquals(valor, tpvController.getCanviVenda(), 0.01);

    }

    @Donat("^existeix el producte \"([^\"]*)\", amb codi de barres \"([^\"]*)\", preu Unitat (.+) i iva (.+)$")
    public void existeixElProducteAmbCodiDeBarresPreuUnitatIIva(String nomProducte, String codiBarres, double preuUnitat, double iva) throws Throwable {
        tpvController.afegeixProducteACatalegAmbPreuUnitat(nomProducte,codiBarres,preuUnitat,iva);
    }

    @Aleshores("^el preu Base del producte \"([^\"]*)\" serà (.+)$")
    public void elPreuBaseDelProducteSerà(String nomProducte, double preuBase) throws Throwable {
        assertEquals(preuBase, tpvController.getPreuBaseProducte(nomProducte), 0.001);
    }

    @Aleshores("^el preu Unitat del producte \"([^\"]*)\" serà (.+)$")
    public void elPreuUnitatDelProducteSerà(String nomProducte, double preuIva) throws Throwable {
        assertEquals(preuIva, tpvController.getPreuUnitatProducte(nomProducte), 0.001);
    }

    @Aleshores("^els preusBase dels productes amb iva (.+) es (.+)$")
    public void elsPreusBaseDelsProductesAmbIvaEs(double iva, double preuEsperat) throws Throwable {
        assertEquals(preuEsperat, tpvController.getSumaPreuBaseVendaPerIva(iva), 0.001);
    }

    @I("^el preuTotal dels productes amb iva (.+) es (.+)$")
    public void elPreuTotalDelsProductesAmbIvaEs(double iva, double preuEsperat) throws Throwable {
        assertEquals(preuEsperat, tpvController.getSumaPreuUnitatVendaPerIva(iva), 0.001);
    }

    @I("^la linia (\\d+) del tiquet sera \"([^\"]*)\"$")
    public void laLiniaDelTiquetSera(int num, String linia) {
        try {
            assertEquals(linia,tpvController.getLiniaTiquetVendaActual(num));
        } catch (NoHiHaTiquetException e) {
            this.exception = e;
        }
    }

    @I("^es va fer una venda amb id (\\d+) dels següens productes i seguents unitats$")
    public void esVaFerUnaVendaAmbIdDelsSegüensProductesISeguentsUnitats(int idVenda, Map<String,Integer> productesVenda) throws VendaJaIniciadaException, ProducteNoExisteixException, VendaJaFinalitzadaException, ParseException, VendaNoIniciadaException {

        tpvController.iniciarVendaAmbID(idVenda);

        Set<String> prods = productesVenda.keySet();
        for(String p: prods) {
            tpvController.afegirLiniaVendaPerCodi(productesVenda.get(p), p);
        }
    }

    @I("^es vol indicar una devolucio de (\\d+) unitats del producte \"([^\"]*)\" de la venda (\\d+) sense motiu")
    public void esVolIndicarUnaDevolucioDeUnitatSDelProducteDeLaVendaPelMotiu(int unitats, String codiProd, int idVenda) throws ProducteNoExisteixException, Exception {
        tpvController.afegirDevolucioAVenda(idVenda,codiProd,unitats,"");
    }

    @I("^el preu total es la suma dels productes a vendre menys el de la devolució, es a dir, (.+)$")
    public void elPreuTotalEsLaSumaDelsProductesAVendreMenysElDeLaDevolucióEsADir(double expectedpreu) {
        assertEquals(expectedpreu, tpvController.getVendaActual().getPreuTotal(), 0.001);
    }

    @I("^indico que el client paga (.+) euros en efectiu$")
    public void indicoQueElClientPagaEurosEnEfectiu(double preuPagament) {
        try {
            tpvController.setPreuPagamentAmbEfectiu(preuPagament);
        } catch (ModeDePagamentIncorrecteException e) {
            this.exception = e;
        }
    }

    @I("^indico que el client paga (.+) euros amb la tarjeta \"([^\"]*)\"")
    public void indicoQueElClientPagaEurosAmbTarjeta(double preuPagament, String numTarjeta) {
        try {
            tpvController.pagamentAmbTarjeta(preuPagament, numTarjeta);
        } catch (ModeDePagamentIncorrecteException | TarjetaNoValidaException e) {
            this.exception = e;
        }
    }

    @I("^l'empleat que ha iniciat la venda es diu \"([^\"]*)\"$")
    public void lEmpleatQueHaIniciatLaVendaEsDiu(String nom) {
        tpvController.getVendaActual().setNomEmpleat(nom);
    }

    @Donat("^el TPV esta a la botiga \"([^\"]*)\"$")
    public void elTPVEstaALaBotiga(String nomBotiga) {
        tpvController.setNomBotiga(nomBotiga);
    }

    @I("^es marca el nom de la botiga -ja definit al TPV- a la venda$")
    public void esMarcaElNomDeLaBotigaJaDefinitAlTPVALaVenda() {
        tpvController.setNomBotigaVendaDefinitATPV();
    }

    @Quan("^passo pel tpv (\\d+) producte amb codi de barres \"([^\"]*)\"$")
    public void passoPelTpvProducteAmbCodiDeBarres(int quantitat, String codiBarrres) throws Throwable {
        tpvController.afegirLiniaVendaPerCodi(quantitat, codiBarrres);
    }

    @Quan("^introdueixo al tpv (\\d+) producte amb nom \"([^\"]*)\"$")
    public void introdueixoAlTpvProducteAmbNom(int unitats, String nomProducte) throws Throwable {
        tpvController.afegirLiniaVendaPerNom(unitats, nomProducte);
    }

    @Quan("^inicio un torn amb nom empleat \"([^\"]*)\"$")
    public void inicioUnTornAmbNomEmpleat(String nomEmpleat) throws Throwable {
        tpvController.iniciarTorn(nomEmpleat);
    }

    @Aleshores("^el TPV està sent usat pel \"([^\"]*)\"$")
    public void elTPVEstàSentUsatPel(String nomEmpleat) throws Throwable {
        assertEquals(nomEmpleat,tpvController.getTornActual().getNomEmpleat());
    }

    @I("^el \"([^\"]*)\" ha iniciat un torn")
    public void tornJaIniciat(String nomEmpleat) throws Throwable {
        tpvController.iniciarTorn(nomEmpleat);
    }

    @I("^cancel·lo el torn sense introduir efectiu inicial")
    public void cancelarTornSenseEfectiuInicial() {
        tpvController.cancelaAccioTorn();
    }

    @Quan("^finalitzo el torn$")
    public void finalitzoElTorn() throws Throwable {
        tpvController.finalitzaTorn();
    }

    @Aleshores("^el torn segueix actiu$")
    public void tornNoFinalitzat() throws Throwable {
        assertEquals(true,tpvController.getTornActual() != null);
    }

    @I("^cancel·lo la finalitzacio del torn$")
    public void canceloLaFinalitzacioDelTorn() throws Throwable {
        tpvController.cancelaAccioTorn();
    }

    @Quan("^indico que vull visualitzar els desquadraments$")
    public void indicoQueVullVisualitzarElsDesuadraments() throws Throwable {
        tpvController.calcularDesquadraments();
    }

    @Aleshores("^obtinc (\\d+) linies$")
    public void obtincLinies(int numVendes) throws Throwable {
        assertEquals(numVendes, tpvController.getNumLineasQuadrament());
    }

    @I("^que estem a dia i hora \"([^\"]*)\"$")
    public void queEstemADiaIHora(String dataIHora) {
        try {
            tpvController.setDataIHora(dataIHora);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @I("^es vol indicar una devolucio de (\\d+) unitats del producte \"([^\"]*)\" de la venda (\\d+) pel motiu \"([^\"]*)\"$")
    public void esVolIndicarUnaDevolucioDeUnitatsDelProducteDeLaVendaPelMotiu(int unitats, String codiBarres, int idVenda, String motiu) throws ProducteNoExisteixException, Exception {
        tpvController.afegirDevolucioAVenda(idVenda,codiBarres,unitats,motiu);
    }


    @Aleshores("^\"([^\"]*)\" es el motiu de l'ultima devolucio$")
    public void esElMotiuDeLaUltimaDevolucio(String motiu) {
        assertEquals(tpvController.getUltimaDevolucio().getMotiu(),motiu);

    }

    @I("^que no hi ha cap torn iniciat$")
    public void queNoHiHaCapTornIniciat() {
        tpvController.setTornActual(null);
    }

    @I("^la linia (\\d+) sera \"([^\"]*)\"$")
    public void laLiniaSera(int numLinia, String linia)  {
        assertEquals(tpvController.getLiniaQuadrament(numLinia), linia);
    }

    @Donat("^que hi ha una venda iniciada amb dues linies linies de venda amb 2 productes 222 i un 333$")
    public void queHiHaUnaVendaIniciadaAmbLiniesDeVenda1() {
        try {
            tpvController.iniciarVenda();
            tpvController.afegirLiniaVendaPerCodi(2, "222");
            tpvController.afegirLiniaVendaPerCodi(1, "333");
        } catch (VendaJaIniciadaException | ProducteNoExisteixException e) {
            this.exception = e;
        }
    }

    @Quan("^indico que el client vol pagar en efectiu")
    public void indicoQueElClientVolPagarEnEfectiu() {
        tpvController.getVendaActual().setTipusPagamentEfectiu();
    }

    @Quan("^indico que el client vol pagar amb tarjeta$")
    public void indicoQueElClientVolPagarAmbTarjeta() {
        tpvController.getVendaActual().setTipusPagamentTarjeta();
    }

    @Aleshores("^la tarjeta es invalida$")
    public void laTarjetaEsInvalida() {
        assertEquals("Error: El mode de pagament és incorrecte.", exception.getMessage());
    }

    @Quan("^accepto el desquadrament$")
    public void acceptoElDesquadrament() throws Throwable {
        tpvController.acceptoDesquadrament();
    }

    @Aleshores ("^el torn està finalitzat")
    public void elTornEstaFinalitzat() throws Throwable {
        assertEquals(true, tpvController.getTornActual() == null);
    }

    @Aleshores("^el tiquet mostra les següents devolucions$")
    public void elTiquetMostraLesSegüentsDevolucions(List<String> list) {
        assertEquals(true,tpvController.existsLiniaTiquet(list));
    }

    @Aleshores("^el tpv m'indica \"([^\"]*)\"$")
    public void elTpvMIndica(String missatge) throws Throwable {
        assertEquals(tpvController.getScreen(),missatge);
    }

    @Quan("^inicio una nova venda amb id (\\d+)$")
    public void inicioUnaNovaVendaAmbId(int idVenda) throws Throwable {
        tpvController.iniciarVendaAmbID(idVenda);
    }


    @I("^la venda (\\d+) no te assignada cap devolucio$")
    public void laVendaNoTeAssignadaCapDevolucio(int idVenda)  {
        assertEquals(0, tpvController.getNumDevolucionsVenda(idVenda));
    }

    @Aleshores("^la venda (\\d+) conte una devolucio del producte \"([^\"]*)\" de la venda (\\d+)$")
    public void laVendaConteUnaDevolucioDelProducteDeLaVenda(int expectedIdVendaRetorn, String codiProd, int idVendaCompra) {
        assertEquals(expectedIdVendaRetorn, tpvController.getIdVendaRetorn(idVendaCompra, codiProd, 1));

    }
}
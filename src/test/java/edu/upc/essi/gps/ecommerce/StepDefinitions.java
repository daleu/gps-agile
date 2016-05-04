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
import javafx.util.Pair;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public void obtincUnErrorQueDiu(String expectedMessage) throws Throwable {
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

    @Quan("^passo pel tpv el codi de barres \"([^\"]*)\"$")
    public void passoPelTpvElCodiDeBarres(String codiBarres) throws Throwable {
        tpvController.passarCodi(codiBarres);
    }

    @Quan("^introdueixo al tpv el producte per nom \"([^\"]*)\"$")
    public void introdueixoAlTpvElProductePerNom(String nomProducte) throws Throwable {
        tpvController.introduirNomProducte(nomProducte);
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

    @I("^el client paga (.+) euros en efectiu$")
    public void elClientPagaEurosEnEfectiu(double valor) throws Throwable {
        tpvController.setPreuPagament(valor);
    }

    @I("^s'afegeix a la linia de venda (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void sAfegeixALaLiniaDeVendaUnitatsDelProducteAmbCodiDeBarres(int unitatsProd, String codiBarres) throws Throwable {
        tpvController.afegirProducteLiniaVenda(codiBarres, unitatsProd);
    }

    @Quan("^introdueixo al tpv (.+) inicials$")
    public void introduirEfectiuInicial(double efectiu) throws Throwable {
        tpvController.setEfectiuInicial(efectiu);
    }

    @Aleshores("^el tpv té (.+) inicials$")
    public void elTpvTeInicials(double inicial) throws Throwable {
        assertEquals(inicial, tpvController.getEfectiuInicial(), 0.0);
    }

    @Quan("^introdueixo al tpv (.+) finals$")
    public void introduirEfectiuFinal(double efectiu) throws Throwable {
        tpvController.setEfectiuFinal(efectiu);
    }

    @Aleshores("^el tpv té (.+) finals$")
    public void elTpvTeFinals(double fin) throws Throwable {
        assertEquals(fin, tpvController.getEfectiuFinal(), 0.0);
    }

    @Aleshores("^obtinc un missatge que diu \"([^\"]*)\"$")
    public void obtenirMissatge(String msg) {
        assertEquals(msg, tpvController.getEstatQuadrament());
    }

    @Donat("^que hi ha al tpv (.+) inicials")
    public void elTpvHiHaInicials(double efectiu) throws Throwable {
        tpvController.setEfectiuInicial(efectiu);
    }

    @I("^que hi ha una linia de venda amb (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void HiHaUnaLiniaDeVenda(int unitats, String codi) throws Throwable {
        tpvController.afegirProducteLiniaVenda(codi, unitats);
    }

    @I("^demano el quadrament")
    public void demanoQuadrament() {
        tpvController.quadrament();
    }


    @Quan("^vull fer una devolucio$")
    public void vullFerUnaDevolucio() {
        tpvController.iniciarDevolucio();
    }


    @Aleshores("^existeix una devolucio del producte \"([^\"]*)\" de la venda (\\d+) pel motiu \"([^\"]*)\"$")
    public void existeixUnaDevolucioDelProducteDeLaVendaPelMotiu(String expectedCodiBarres, int expectedIdVenda, String expectedMotiu) throws Throwable {
        assertEquals(expectedCodiBarres, tpvController.getCodiBarresDevolucio(expectedIdVenda, expectedCodiBarres, 1));
        assertEquals(expectedIdVenda, tpvController.getIdVendaDevolucio(expectedIdVenda, expectedCodiBarres, 1));
        assertEquals(expectedMotiu, tpvController.getMotiuDevolucio(expectedIdVenda, expectedCodiBarres, 1));
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
        assertEquals(valor, tpvController.getCanviUltimaVenda(), 0.001);

    }

    @Donat("^existeix el producte \"([^\"]*)\", amb codi de barres \"([^\"]*)\", preu Base (.+) i iva (.+)$")
    public void existeixElProducteAmbCodiDeBarresPreuBaseIIva(String nomProducte, String codiBarres, double preuBase, double iva) throws Throwable {
        tpvController.afegeixProducteACatalegAmbIva(nomProducte,codiBarres,preuBase,iva);
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
    public void esVaFerUnaVendaAmbIdDelsSegüensProductesISeguentsUnitats(int idVenda, Map<String,Integer> productesVenda) throws VendaJaIniciadaException, ProducteNoExisteixException {

        tpvController.introduirVendaJaAcabada(idVenda,productesVenda);
    }

    @I("^es vol indicar una devolucio de (\\d+) unitats del producte \"([^\"]*)\" de la venda (\\d+) pel motiu \"([^\"]*)\"$")
    public void esVolIndicarUnaDevolucioDeUnitatSDelProducteDeLaVendaPelMotiu(int unitats, String codiProd, int idVenda, String motiu) throws ProducteNoExisteixException, Exception {
        tpvController.introduirDevolucio(idVenda,codiProd,unitats,motiu);


    }

    @I("^el preu total es la suma dels productes a vendre menys el de la devolució, es a dir, (.+)$")
    public void elPreuTotalEsLaSumaDelsProductesAVendreMenysElDeLaDevolucióEsADir(double expectedpreu) {
        assertEquals(expectedpreu, tpvController.getVendaActual().getPreuTotal(), 0.001);
    }

}
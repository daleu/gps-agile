package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;
import edu.upc.essi.gps.ecommerce.domain.*;
import edu.upc.essi.gps.ecommerce.exceptions.*;
import edu.upc.essi.gps.ecommerce.repositoris.VendesRepositori;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinitions {
    /*
     Enter (\\d+)
     Double (.+)
     String \"([^\"]*)\"
      */

    VendesRepositori vendesRepositori = new VendesRepositori();
    VendesServei vendesServei = new VendesServei(vendesRepositori);
    CtrlLlocActual ctrlLlocActual = new CtrlLlocActual(vendesServei);

    private Exception exception;
    @Quan("^inicio una venda$")
    public void iniciarVenda() {
        try {
            TPV.getInstance().iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^la venda no te linies de venda$")
    public void la_venda_no_te_linies_de_venda() throws Throwable {
        Venda venda = TPV.getInstance().getVendaActual();
        assertEquals(true, venda.isEmpty());
    }

    @Aleshores("^el preu total de la venda es (.+)$")
    public void el_preu_total_de_la_venda_es_(double totalExpected) throws Throwable {
        Venda venda = TPV.getInstance().getVendaActual();
        assertEquals(totalExpected, venda.getTotal(),0.001);
    }

    @Aleshores("^la venda te per identificador (\\d+)$")
    public void la_venda_te_per_identificador(int expectedId) throws Throwable {
        Venda venda = TPV.getInstance().getVendaActual();
        assertNotNull(venda);
        assertEquals(expectedId, venda.getId());
    }

    @Donat("^que hi ha una venda iniciada$")
    public void que_hi_ha_una_venda_iniciada() throws Throwable {
        try {
            TPV.getInstance().iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Quan("^inicio una nova venda$")
    public void inicio_una_nova_venda() throws Throwable {
        try {
            TPV.getInstance().iniciarVenda();
        } catch (VendaJaIniciadaException e) {
            this.exception = e;
        }
    }

    @Aleshores("^obtinc un error que diu \"([^\"]*)\"$")
    public void obtinc_un_error_que_diu(String expectedMessage) throws Throwable {
        assertNotNull(this.exception);
        assertEquals(expectedMessage, this.exception.getMessage());
    }

    @Quan("^tanco una venda sense tenir una venda iniciada$")
    public void tanco_una_venda_sense_tenir_una_venda_iniciada() throws Throwable {
        try {
            TPV.getInstance().tancarVendaActual();
        } catch (VendaNoIniciadaException e) {
            this.exception = e;
        }
    }

    @Donat("^que no hi ha cap venda iniciada$")
    public void que_no_hi_ha_cap_venda_iniciada() throws Throwable {
        TPV.getInstance().setVendaActual(null);
    }

    @I("^existeix el producte \"([^\"]*)\" amb codi de barres \"([^\"]*)\" i preu per unitat (.+)$")
    public void existeix_el_producte_amb_codi_de_barres_i_preu_per_unitat_(String nomProducte, String codiBarres, double preuUnitat) throws Throwable {
        Producte p = new Producte(nomProducte, codiBarres, preuUnitat);
        Cataleg.getInstance().afegeixProducte(p);
    }

    @Quan("^passo pel tpv el codi de barres \"([^\"]*)\"$")
    public void passo_pel_tpv_el_codi_de_barres(String codiBarres) throws Throwable {
        TPV.getInstance().passarCodi(codiBarres);
    }

    @Quan("^introdueixo al tpv el producte per nom \"([^\"]*)\"$")
    public void introdueixoAlTpvElProductePerNom(String nomProducte) throws Throwable {
        TPV.getInstance().introduirNomProducte(nomProducte);
    }

    @Aleshores("^la venda te una linia de venda$")
    public void la_venda_te_una_linia_de_venda() throws Throwable {
        assertEquals(1, TPV.getInstance().getVendaActual().getNombreLiniesVenda());
    }

    @I("^la linia de venda (\\d+) te per producte \"([^\"]*)\"$")
    public void la_linia_de_venda_te_per_producte(int i, String expectedNom) throws Throwable {
        assertEquals(expectedNom, TPV.getInstance().getVendaActual().getLiniaVenda(i).getNomProducte());
    }

    @I("^la linia de venda (\\d+) te per preu unitat (.+)$")
    public void la_linia_de_venda_te_per_preu_unitat_(int i, double expectedPreu) throws Throwable {
        assertEquals(expectedPreu, TPV.getInstance().getVendaActual().getLiniaVenda(i).getTotal(),0.001);
    }

    @I("^la linia de venda (\\d+) te per quantitat (\\d+)$")
    public void la_linia_de_venda_te_per_quantitat(int i, int expectedQuantitat) throws Throwable {
        assertEquals(expectedQuantitat, TPV.getInstance().getVendaActual().getLiniaVenda(i).getQuantitat());
    }

    @I("^es va fer una venda amb el codi \"([^\"]*)\" amb (\\d+) productes amb codi \"([^\"]*)\" i (\\d+) producte amb codi \"([^\"]*)\"$")
    public void esVaFerUnaVendaAmbElCodiAmbProductesAmbCodiIProducteAmbCodi(String codiVenda, int unitatsProd1, String codiProd1, int unitatsProd2, String codiProd2) throws Throwable {
        ctrlLlocActual.iniciarVenda();
        ctrlLlocActual.afegirLiniaVenda(codiProd1,unitatsProd1);
        ctrlLlocActual.afegirLiniaVenda(codiProd2,unitatsProd2);
        ctrlLlocActual.tancarVendaActual();
        throw new PendingException();
    }

    @I("^que hi ha una venda començada$")
    public void queHiHaUnaVendaComençada() throws Throwable {
        ctrlLlocActual.iniciarVenda();
        throw new PendingException();
    }

    @I("^s'afegeix a la linia de venda (\\d+) unitats del producte amb codi de barres \"([^\"]*)\"$")
    public void sAfegeixALaLiniaDeVendaUnitatsDelProducteAmbCodiDeBarres(int unitatsProd, String codiProd) throws Throwable {
       ctrlLlocActual.afegirLiniaVenda(codiProd,unitatsProd);
        throw new PendingException();
    }

    @Quan("^vull retornar (\\d+) unitat del producte amb codi \"([^\"]*)\" de la venda \"([^\"]*)\"$")
    public void vullRetornarUnitatDelProducteAmbCodiDeLaVenda(int arg0, String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Aleshores("^comprobo que la venda existeix$")
    public void comproboQueLaVendaExisteix() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @I("^el producte es va vendre en aquella venda$")
    public void elProducteEsVaVendreEnAquellaVenda() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @I("^indico la devolucio del producte \"([^\"]*)\" pel motiu \"([^\"]*)\"$")
    public void indicoLaDevolucioDelProductePelMotiu(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @I("^la linia venda inserta el producte en devolució en negatiu$")
    public void laLiniaVendaInsertaElProducteEnDevolucióEnNegatiu() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}

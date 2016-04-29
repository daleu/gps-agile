package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;
import cucumber.api.java.cs.A;
import edu.upc.essi.gps.ecommerce.domain.*;
import edu.upc.essi.gps.ecommerce.exceptions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinitions {
    /*
     Enter (\\d+)
     Double (.+)
     String \"([^\"]*)\"
      */

    private Exception exception;

    public void tryCatch(Runnable r){
        try {
            r.run();
            this.exception = null;
        } catch (Exception e){
            this.exception = e;
        }
    }

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
        assertEquals(totalExpected, venda.getTotal(),0.0);
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
}

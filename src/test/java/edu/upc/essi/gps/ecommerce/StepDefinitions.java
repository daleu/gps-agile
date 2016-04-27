package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinitions {
    /*
     Enter (\\d+)
     Double (.+)
     String \"([^\"]*)\"
      */
    private Venda venda;
    private LiniaVenda ultimaLiniaVenda;
    private Exception exception;
    private int change;
    private double preuTotal;

    public void tryCatch(Runnable r){
        try {
            r.run();
            this.exception = null;
        } catch (Exception e){
            this.exception = e;
        }
    }

    @Quan("^inicio una venda nova$")
    public void startVenda() throws Throwable {
        try {
            if (venda != null) throw new IllegalStateException("Ja hi ha una venda iniciada");
            else this.venda = new Venda();
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Aleshores("^no hi ha linies de venda$")
    public void comprovarVendaBuida() throws Throwable {
        assertEquals(true, venda.isEmpty());
    }

    @Donat("^que hi ha una venda iniciada$")
    public void vendaIniciada() throws Throwable {
        this.venda = new Venda();
    }

    @Quan("^afegeixo una linia de venda amb nom de producte \"([^\"]*)\", amb preu (.+) i amb quantitat (\\d+)$")
    public void afegirLiniaVenda(String nomProducte, double preuUnitat, int quantitat) throws Throwable {
        ultimaLiniaVenda = new LiniaVenda(nomProducte, preuUnitat, quantitat);
        venda.afegirLiniaVenda(ultimaLiniaVenda);
    }

    @Aleshores("^la ultima linia de venda te nom de producte \"([^\"]*)\", preu (.+) i quantitat (\\d+)$")
    public void laUltimaLiniaVendaTe(String nomProducte, double preuUnitat, int quantitat) throws Throwable {
        assertEquals(nomProducte,ultimaLiniaVenda.getNomProducte());
        assertEquals(preuUnitat,ultimaLiniaVenda.getPreuUnitat(),0.0);
        assertEquals(quantitat,ultimaLiniaVenda.getQuantitat());
    }

    @Quan ("^finalitzo una venda$")
    public void endVenda() throws Throwable,Exception {
        venda.tancarVenda();
    }

    @Aleshores("^obtinc un missatge que diu \"([^\"]*)\"$")
    public void missatge(String msg) throws Throwable {
        assertEquals(msg,venda.getMessage());
        System.out.println(msg);
    }

    @Donat ("^que hi ha una linia de venda amb nom de producte \"([^\"]*)\", amb preu (.+) i amb quantitat (\\d+)$")
    public void hiHaUnaLiniaVendaAmb (String nomProducte, double preuUnitat, int quantitat) throws Throwable {
        afegirLiniaVenda(nomProducte,preuUnitat,quantitat);
    }

    @I("^preu total (.+)$")
    public void preuTotal(double p) throws Throwable {
        assertEquals(p, venda.getTotal(), 0.0);
    }

    @Quan("^demano el preu total de la ultima linia de venda$")
    public void demanoElPreuTotalDeLaUltimaLiniaDeVenda() throws Throwable {
        this.preuTotal = ultimaLiniaVenda.getPreuTotal();
    }

    @Aleshores("^la ultima linia de venda te preu total (.+)$")
    public void laUltimaLiniaDeVendaTePreuTotal(double expectedPreuTotal) throws Throwable {
        assertEquals(expectedPreuTotal, preuTotal, 0.0);
    }

    @Aleshores("^obtinc un error que diu: \"([^\"]*)\"$")
    public void obtincUnErrorQueDiu(String missatgeError) throws Throwable {
        assertNotNull(this.exception);
        assertEquals(missatgeError, this.exception.getMessage());
    }
}

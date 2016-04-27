package edu.upc.essi.gps.ecommerce;

import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
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
        tryCatch(() -> this.venda = new Venda());
    }

    @Aleshores("^no hi ha linies de venda$")
    public void comprovarVendaBuida() throws Throwable {
        assertEquals(true, venda.isEmpty());
    }

    @Donat("^que hi ha una venda$")
    public void vendaIniciada() throws Throwable {
        this.venda = new Venda();
    }

    @Quan("^afegeixo una linia de venda amb nom de producte \"([^\"]*)\", amb preu (.+) i amb quantitat (\\d+)$")
    public void afegirLiniaVenda(String nomProducte, double preuUnitat, int quantitat){
        ultimaLiniaVenda = new LiniaVenda(nomProducte, preuUnitat, quantitat);
        venda.afegirLiniaVenda(ultimaLiniaVenda);
    }

    @Aleshores("^la ultima linia de venda te nom de producte \"([^\"]*)\", preu (.+) i quantitat (\\d+)$")
    public void laUltimaLiniaVendaTe(String nomProducte, double preuUnitat, int quantitat){
        assertEquals(nomProducte,ultimaLiniaVenda.getNomProducte());
        assertEquals(preuUnitat,ultimaLiniaVenda.getPreuUnitat(),0.0);
        assertEquals(quantitat,ultimaLiniaVenda.getQuantitat());
    }

}

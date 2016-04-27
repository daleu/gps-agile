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
    private TerminalVenda terminalVenda;
    private Exception exception;
    private int change;
    private double preuTotal;

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

    @I("^hi ha una linia de venda amb nom de producte \"([^\"]*)\", amb preu (.+) i amb quantitat (\\d+)$")
    public void hiHaLiniaVenda(String nomProducte, double preuUnitat, int quantitat) throws Throwable {
        ultimaLiniaVenda = new LiniaVenda(nomProducte, preuUnitat, quantitat);
        venda.afegirLiniaVenda(ultimaLiniaVenda);
    }

    @Quan("^afegeixo una linia de venda amb nom de producte \"([^\"]*)\", amb preu (.+) i amb quantitat (\\d+)$")
    public void afegirLiniaVenda(String nomProducte, double preuUnitat, int quantitat) throws Throwable {
        ultimaLiniaVenda= new LiniaVenda(nomProducte, preuUnitat, quantitat);
        venda.afegirLiniaVenda(ultimaLiniaVenda);
    }

    @Quan("^afegeixo una altra linia de venda amb nom de producte \"([^\"]*)\", amb preu (.+) i amb quantitat (\\d+)$")
    public void afegirAltraLiniaVenda(String nomProducte, double preuUnitat, int quantitat) throws Throwable {
        LiniaVenda auxLiniaVenda = new LiniaVenda(nomProducte, preuUnitat, quantitat);
        venda.afegirLiniaVenda(auxLiniaVenda);
    }

    @Aleshores("^la ultima linia de venda te nom de producte \"([^\"]*)\", preu (.+) i quantitat (\\d+)$")
    public void laUltimaLiniaVendaTe(String nomProducte, double preuUnitat, int quantitat) throws Throwable {
        assertEquals(nomProducte, ultimaLiniaVenda.getNomProducte());
        assertEquals(preuUnitat, ultimaLiniaVenda.getPreuTotal(), 0.0);
        assertEquals(quantitat, ultimaLiniaVenda.getQuantitat());
    }

    @Quan ("^finalitzo una venda$")
    public void endVenda() throws Throwable {
        venda.tancarVenda(false);
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

    @I ("^el client paga (.+)")
    public void faPagament(double c) throws Throwable {
        assertEquals(true,c >= venda.getTotal());
        this.venda.setPagament(c);
    }

    @I ("^canvi (.+)")
    public void retornCanvi (double canvi) throws Throwable {
        assertEquals(canvi,this.venda.getCanvi(),0.0);
    }

    @Quan ("^anul·lo una venda$")
    public void anularVenda() throws Throwable {
        venda.tancarVenda(true);
    }

    @I("^obtinc un ticket que diu \"([^\"]*)\"$")
    public void ticket (String tck) throws Throwable {
        String t = this.venda.getTicket();
        assertEquals(tck,t);
        System.out.println(t);
    }

    @Donat("^que en \"([^\"]*)\" ha iniciat el torn al \"([^\"]*)\" de \"([^\"]*)\"$")
    public void queEnHaIniciatElTornAlDe(String nomEmpleat, String nomTerminal, String nomPoblacio) throws Throwable {
        terminalVenda = new TerminalVenda(nomEmpleat,nomTerminal,nomPoblacio);
    }

    @I("^afegeixo una linia de venda amb nom de producte \"([^\"]*)\", amb preu (.+), amb quantitat (\\d+) i IVA (\\d+)$")
    public void afegeixoUnaLiniaDeVendaAmbNomDeProducteAmbPreuAmbQuantitatIIVA(String nomProducte, double preuUnitat, int quantitat, int iva) throws Throwable {
        ultimaLiniaVenda= new LiniaVenda(nomProducte, preuUnitat, quantitat, iva);
        venda.afegirLiniaVenda(ultimaLiniaVenda);
    }

    @Quan("^demano el preu total amb iva de la linia de venda$")
    public void demanoElPreuTotalAmbIvaDeLaLiniaDeVenda() throws Throwable {
        this.preuTotal = venda.getTotalIva();
    }

    @Aleshores("^la linia de venda te preu total (.+)$")
    public void laLiniaDeVendaTePreuTotal(double expectedPreuTotal) throws Throwable {
        assertEquals(expectedPreuTotal, preuTotal, 0.0);
    }
}

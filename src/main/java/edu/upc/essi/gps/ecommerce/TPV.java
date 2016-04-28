package edu.upc.essi.gps.ecommerce;

/**
 * Created by edu on 28/04/16.
 */
public class TPV {
    private int numVendesDiaries = 0;
    private Venda vendaActual;

    public void iniciarVenda() {
        ++numVendesDiaries;
        vendaActual = new Venda(numVendesDiaries);
    }

    public TPV(String nomEmpleat, String nomTerminal, String nomPoblacio) {
    }
}

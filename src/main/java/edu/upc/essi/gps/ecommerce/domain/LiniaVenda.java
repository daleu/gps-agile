package edu.upc.essi.gps.ecommerce.domain;

import java.text.DecimalFormat;

/**
 * Created by edu on 28/04/16.
 */
public class LiniaVenda {
    private Producte producte;
    private int quantitat;

    public LiniaVenda(Producte p){
        this.producte = p;
        quantitat = 1;
    }

    public double getTotal() {
        return producte.getPreuUnitat()*quantitat;
    }

    public String getNomProducte() {
        return producte.getNom();
    }

    public int getQuantitat() {
        return quantitat;
    }
}

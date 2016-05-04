package edu.upc.essi.gps.ecommerce.domain;

import java.text.DecimalFormat;

/**
 * Created by edu on 28/04/16.
 */
public class LiniaVenda {
    private Producte producte;
    private int quantitat;

    public LiniaVenda(Producte p,Integer q){
        this.producte = p;
        quantitat = q;
    }

    public double getPreuTotal() {
        return producte.getPreuUnitat()*quantitat;
    }

    public String getNomProducte() {
        return producte.getNom();
    }

    public int getQuantitat() {
        return quantitat;
    }

    public String getCodiProducte() {
        return producte.getCodiBarres();
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public double getPreuUnitat() {return this.producte.getPreuUnitat();}
}

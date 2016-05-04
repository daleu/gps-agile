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

    public void incrementaQuantitat(Integer unitats) {
        this.quantitat += unitats;
    }

    public double getPreuUnitat() {
        return producte.getPreuUnitat();
    }


    public double getTotalPreuBase(double iva) {
        if(producte.mateixIva(iva)) return producte.getPreuBase()*quantitat;
        else return 0.0;
    }

    public double getTotalUnitatBase(double iva) {
        if(producte.mateixIva(iva)) return producte.getPreuUnitat()*quantitat;
        else return 0.0;
    }
}

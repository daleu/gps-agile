package edu.upc.essi.gps.ecommerce;

import java.text.DecimalFormat;

/**
 * Created by edu on 28/04/16.
 */
public class LiniaVenda {
    private String nomProducte;
    private double preuUnitat;
    private double preuUnitatMesIva;
    private int quantitat;

    public LiniaVenda(String nomProducte, double preuUnitat, int quantitat) {
        this.nomProducte = nomProducte;
        this.preuUnitat = preuUnitat;
        this.quantitat = quantitat;
    }

    public LiniaVenda(String nomProducte, double preuUnitat, int quantitat, int iva) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.nomProducte = nomProducte;
        this.preuUnitat = preuUnitat;
        this.quantitat = quantitat;
        if (iva == 21) this.preuUnitatMesIva = preuUnitat + preuUnitat*0.21;
        else this.preuUnitatMesIva = preuUnitat + preuUnitat*0.04;
        this.preuUnitatMesIva = Math.round(preuUnitatMesIva*100.0)/100.0;
    }

    public String getNomProducte() {
        return nomProducte;
    }

    public double getPreuUnitat() {
        return preuUnitat;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public double getPreuTotal() {
        return preuUnitat * quantitat;
    }

    public double getPreuTotalAmbIva() {
        return preuUnitatMesIva * quantitat;
    }

    public void sumQuantitat(int quantitat) { this.quantitat += quantitat; }
}

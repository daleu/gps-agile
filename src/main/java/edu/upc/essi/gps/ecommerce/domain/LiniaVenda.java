package edu.upc.essi.gps.ecommerce.domain;

import java.text.DecimalFormat;

/**
 * Created by edu on 28/04/16.
 */
public class LiniaVenda {
    private double preuUnitat;
    private int quantitat;

    public LiniaVenda(){};

    public double getTotal() {
        return preuUnitat*quantitat;
    }

}

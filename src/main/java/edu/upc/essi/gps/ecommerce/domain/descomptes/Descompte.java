package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public abstract class Descompte {
    private String codiDeBarres;
    private Calendar dataCaducitat;
    private double descompte;

    protected Descompte(String codiDeBarres, double descompte, Calendar dataCaducitat) {
        this.codiDeBarres = codiDeBarres;
        this.descompte = descompte;
        this.dataCaducitat = dataCaducitat;
    }

    public Calendar getDataCaducitat() {
        return dataCaducitat;
    }

    public double getDescompte() {
        return descompte;
    }
}

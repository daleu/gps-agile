package edu.upc.essi.gps.ecommerce.domain.descomptes;

import edu.upc.essi.gps.domain.Entity;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public abstract class Descompte implements Entity {
    protected int id;
    protected Calendar dataCaducitat;
    protected double descompte;

    protected Descompte(int id, double descompte, Calendar dataCaducitat) {
        this.id = id;
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

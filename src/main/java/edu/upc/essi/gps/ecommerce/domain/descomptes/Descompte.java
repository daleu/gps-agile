package edu.upc.essi.gps.ecommerce.domain.descomptes;

import edu.upc.essi.gps.domain.Entity;

import java.util.Calendar;


public abstract class Descompte implements Entity {
    private int id;
    private Calendar dataCaducitat;
    private double descompte;

    protected Descompte(int id, double descompte, Calendar dataCaducitat) {
        this.id = id;
        this.descompte = descompte;
        this.dataCaducitat = dataCaducitat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDataCaducitat() {
        return dataCaducitat;
    }

    public double getDescompte() {
        return descompte;
    }
}

package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public abstract class Descompte {
    private int id;
    private Calendar dataCaducitat;
    private boolean usat;
    private double descompte;

    public Descompte (int id) {
        this.id = id;
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

    public void setDataCaducitat(Calendar dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    public boolean isUsat() {
        return usat;
    }

    public void setUsat(boolean usat) {
        this.usat = usat;
    }

    public double getDescompte() {
        return descompte;
    }

    public void setDescompte(double descompte) {
        this.descompte = descompte;
    }
}

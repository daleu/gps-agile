package edu.upc.essi.gps.ecommerce.domain;

/**
 * Created by eduard.maura.i on 29/04/2016.
 */
public class Producte {
    private double preuUnitat;
    private String codiBarres;
    private String nom;

    public Producte(String nomProducte, String codiBarres, double preuUnitat) {
        nom = nomProducte;
        this.codiBarres = codiBarres;
        this.preuUnitat = preuUnitat;
    }

    public double getPreuUnitat() {
        return preuUnitat;
    }

    public String getCodiBarres() {
        return codiBarres;
    }

    public String getNom() {
        return nom;
    }
}

package edu.upc.essi.gps.ecommerce.domain;

/**
 * Created by eduard.maura.i on 29/04/2016.
 */
public class Producte {
    private double preuUnitat; //Preu Amb IVA
    private double iva;        //iva (eL PREU bASE S'HA DE CALCULAR)
    private String codiBarres;
    private String nom;

    public Producte(String nomProducte, String codiBarres, double preuUnitat) {
        this.nom = nomProducte;
        this.codiBarres = codiBarres;
        this.preuUnitat = preuUnitat;
    }

    public Producte(String nomProducte, String codiBarres, double preuBase, double iva) {
        this.nom = nomProducte;
        this.codiBarres = codiBarres;
        this.iva = iva;
        this.preuUnitat = preuBase+(preuBase*iva);
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

    public double getPreuBase() {
        return (preuUnitat/(1+iva));
    }
}

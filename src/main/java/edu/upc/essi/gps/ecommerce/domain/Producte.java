package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.domain.ofertes.Oferta;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaNxM;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaPercentatge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;

/**
 * Created by eduard.maura.i on 29/04/2016.
 */
public class Producte {
    private double preuUnitat; //Preu Amb IVA
    private double iva;        //iva (eL PREU BASE S'HA DE CALCULAR)
    private String codiBarres;
    private String nom;
    private ArrayList <Oferta> ofertes = new ArrayList <Oferta>();

    public Producte(String nomProducte, String codiBarres, double preuUnitat) {
        this.nom = nomProducte;
        this.codiBarres = codiBarres;
        this.preuUnitat = preuUnitat;
        this.iva=0.21; //Predefinit
    }

    public Producte(String nomProducte, String codiBarres, double preuUnitat, double iva) {
        this.nom = nomProducte;
        this.codiBarres = codiBarres;
        this.iva = iva;
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

    public double getPreuBase() {
        return (preuUnitat/(1+iva));
    }

    public boolean mateixIva(double iva) {
        return this.iva == iva;
    }

    public double getIVA() {return this.iva;}

    public void afegirOfertaNxM(int id, int N, int M, Calendar calendarInici, Calendar calendarFinal) {
        if (!existeixOferta(id)) {
            OfertaNxM oferta = new OfertaNxM(id, N, M, calendarInici, calendarFinal);
            ofertes.add(oferta);
        }
    }

    public boolean existeixOferta(int id) {
        ListIterator<Oferta> index = ofertes.listIterator();
        boolean trobat = false;
        while (!trobat && index.hasNext()) {
            trobat = (index.next().getId() == id);
        }
        return trobat;
    }

    public void afegirOfertaPercentatge (int id, int percentatge, Calendar calendarInici, Calendar calendarFinal) {
        if (!existeixOferta(id)) {
            OfertaPercentatge oferta = new OfertaPercentatge(id, percentatge, calendarInici, calendarFinal);
            ofertes.add(oferta);
        }
    }
}

package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.domain.ofertes.Oferta;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaNxM;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaPercentatge;
import edu.upc.essi.gps.ecommerce.domain.ofertes.OfertaRegal;

import java.text.SimpleDateFormat;
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

    public void afegirOfertaRegal (int id, String quantitat, String idRegal, Calendar calendarInici, Calendar calendarFinal) {
        if (!existeixOferta(id)) {
            OfertaRegal oferta = null;
            oferta = new OfertaRegal(id, quantitat, idRegal, calendarInici, calendarFinal);
            ofertes.add(oferta);
        }
    }

    public String getOfertesProducte() {
        StringBuilder llista = new StringBuilder();
        Oferta ofert;
        if (ofertes.size() > 0) {
            llista.append(nom + " | ");
            ListIterator<Oferta> index_ofertes = ofertes.listIterator();
            while (index_ofertes.hasNext()) {
                ofert = index_ofertes.next();
                if (ofert instanceof OfertaNxM) {
                    llista.append(String.valueOf(((OfertaNxM) ofert).getN()) + "x" +
                            String.valueOf(((OfertaNxM) ofert).getM()) + " | ");
                } else if (ofert instanceof OfertaPercentatge) {
                    llista.append(String.valueOf(((OfertaPercentatge) ofert).getPercentatge()) + "% | ");
                } else if (ofert instanceof OfertaRegal) {
                    llista.append("Regalem: " + ((OfertaRegal) ofert).getIdRegal() +
                            " | Quantitat: " + ((OfertaRegal) ofert).getQuantitat() +
                            " | ");
                }
                SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
                String dataI = dF.format(ofert.getDataInici().getTime());
                String dataF = dF.format(ofert.getDataFinal().getTime());
                llista.append(dataI + " | " + dataF + " |");
                if (index_ofertes.hasNext()) { llista.append(" - "); }
            }
        }
        return llista.toString();
    }

    public ArrayList <Oferta> getOfertes() { return this.ofertes; }
}

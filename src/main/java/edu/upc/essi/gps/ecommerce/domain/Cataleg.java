package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.ProducteNoExisteixException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduard.maura.i on 29/04/2016.
 */
public class Cataleg {
    //Classe singleton
    private Map<String, Producte> productesPerCodi;
    private Map<String, Producte> productesPerNom;


    public Cataleg() {
        this.productesPerNom = new HashMap<String,Producte>();
        this.productesPerCodi = new HashMap<String,Producte>();
    }

    public void afegeixProducte(Producte p) {
        productesPerNom.put(p.getNom(), p);
        productesPerCodi.put(p.getCodiBarres(), p);
    }

    public Producte getProductePerCodi(String codiBarres) throws ProducteNoExisteixException {
        Producte p = productesPerCodi.get(codiBarres);
        if (p == null) throw new ProducteNoExisteixException();
        return p;
    }

    public Producte getProductePerNom(String nomProducte) throws ProducteNoExisteixException {
        Producte p = productesPerNom.get(nomProducte);
        if (p == null) throw new ProducteNoExisteixException();
        return p;
    }

    public boolean existeixProducte(String codiProducte) throws ProducteNoExisteixException {
        if (this.getProductePerCodi(codiProducte) == null) return false;
        return true;
    }

    public double getPreuBaseProducte(String nomProducte) {
        Producte p = productesPerNom.get(nomProducte);
        return p.getPreuBase();
    }

    public double getPreuUnitatProducte(String nomProducte) {
        Producte p = productesPerNom.get(nomProducte);
        return p.getPreuUnitat();
    }

    public ArrayList<Producte> getAllProductesPerNom() {
        ArrayList<Producte> productes = new ArrayList<>();
        for (String key: productesPerNom.keySet()) {
            productes.add(productesPerNom.get(key));
        }
        return productes;
    }
}

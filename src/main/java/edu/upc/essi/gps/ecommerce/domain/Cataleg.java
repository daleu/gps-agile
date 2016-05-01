package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.ProducteNoExisteixException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduard.maura.i on 29/04/2016.
 */
public class Cataleg {
    //Classe singleton
    private static Cataleg instance;
    private Map<String, Producte> productesPerCodi;
    private Map<String, Producte> productesPerNom;

    public static Cataleg getInstance() {
        if (instance == null) instance = new Cataleg();
        return instance;
    }

    private Cataleg() {
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
}

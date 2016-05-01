package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.ProducteNoExisteixException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaJaIniciadaException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaNoIniciadaException;

/**
 * Created by edu on 28/04/16.
 */
public class TPV {
    //Classe singleton
    private static TPV instance;
    private int numVendesDiaries = 0;
    private Venda vendaActual;

    private TPV(){}

    public static TPV getInstance() {
        if (instance == null) instance = new TPV();
        return instance;
    }

    public void iniciarVenda() throws VendaJaIniciadaException {
       if (this.vendaActual == null) {
           ++numVendesDiaries;
           vendaActual = new Venda(numVendesDiaries);
       } else {
           throw new VendaJaIniciadaException();
       }
    }


    public Venda getVendaActual() {
        return vendaActual;
    }

    public void tancarVendaActual() throws VendaNoIniciadaException {
        if (vendaActual != null) vendaActual.tancar();
        else throw new VendaNoIniciadaException();
    }

    public void setVendaActual(Venda vendaActual) {
        this.vendaActual = vendaActual;
    }

    public void passarCodi(String codiBarres) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerCodi(codiBarres);
        vendaActual.afegeixLinia(producteIdentificat);
    }

    public void introduirNomProducte(String nomProducte) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerNom(nomProducte);
        vendaActual.afegeixLinia(producteIdentificat);
    }
}

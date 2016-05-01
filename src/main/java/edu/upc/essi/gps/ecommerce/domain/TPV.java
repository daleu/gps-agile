package edu.upc.essi.gps.ecommerce.domain;

import edu.upc.essi.gps.ecommerce.exceptions.ProducteNoExisteixException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaJaIniciadaException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaNoIniciadaException;
import edu.upc.essi.gps.ecommerce.repositoris.VendesRepositori;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;

/**
 * Created by edu on 28/04/16.
 */
public class TPV {
    //Classe singleton
    private static TPV instance;

    private Venda vendaActual;
    private final VendesRepositori vendesRepositori = new VendesRepositori();
    private final VendesServei vendesServei = new VendesServei(vendesRepositori);

    private TPV(){}

    public static TPV getInstance() {
        if (instance == null) instance = new TPV();
        return instance;
    }

    //---------------------------
    // Estats de venda
    //---------------------------

    public void iniciarVenda() throws VendaJaIniciadaException {
       if (this.vendaActual == null) {
           vendaActual = vendesServei.novaVenda();
       } else {
           throw new VendaJaIniciadaException();
       }
    }

    public void iniciarVendaAmbID(int id) throws VendaJaIniciadaException { //Per a JOCS de PROVA
        if (this.vendaActual == null) {
            vendaActual = vendesServei.novaVendaAmbID(id);
        } else {
            throw new VendaJaIniciadaException();
        }
    }

    public void tancarVendaActual() throws VendaNoIniciadaException {
        if (vendaActual != null)  {
            vendesServei.guardarVenda(vendaActual);
            vendaActual.tancar();
            vendaActual = null;
        }
        else throw new VendaNoIniciadaException();
    }

    public Venda getVendaActual() {
        return vendaActual;
    }

    public void setVendaActual(Venda vendaActual) {
        this.vendaActual = vendaActual;
    }


    //------------------------------
    //INTRODUIR PRODUCTES A UNA VENDA
    //------------------------------

    public void passarCodi(String codiBarres) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerCodi(codiBarres);
        vendaActual.afegeixLinia(producteIdentificat,1);
    }

    public void introduirNomProducte(String nomProducte) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerNom(nomProducte);
        vendaActual.afegeixLinia(producteIdentificat,1);
    }

    public void afegirProducteLiniaVenda(String codiBarres, int unitats) throws ProducteNoExisteixException {
        Producte producteIdentificat = Cataleg.getInstance().getProductePerCodi(codiBarres);
        if(producteIdentificat == null) throw new ProducteNoExisteixException();
        vendaActual.afegeixLinia(producteIdentificat,unitats);
    }

    public boolean possibilitatDeRetorn(int idVenda, String codiBarres, int unitatsProd) {
        Venda ven_anterior = vendesServei.trobaPerCodi(idVenda);
        if(ven_anterior != null) {
            ven_anterior.conteLiniaVenda(codiBarres,unitatsProd);
        }
        return false;
    }
}

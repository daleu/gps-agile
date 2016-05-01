package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.ecommerce.domain.Cataleg;
import edu.upc.essi.gps.ecommerce.domain.Producte;
import edu.upc.essi.gps.ecommerce.domain.Venda;
import edu.upc.essi.gps.ecommerce.exceptions.ProducteNoExisteixException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaJaIniciadaException;
import edu.upc.essi.gps.ecommerce.exceptions.VendaNoIniciadaException;
import edu.upc.essi.gps.ecommerce.repositoris.VendesServei;

public class CtrlLlocActual {
    private final VendesServei vendesServei;
    private Venda vendaActual;


    public CtrlLlocActual(VendesServei vendesServei) {
        this.vendesServei = vendesServei;
    }

    //------------------------------
    //SOBRE VENDA
    //------------------------------

    public void iniciarVenda() throws VendaJaIniciadaException {
        if (this.vendaActual == null) {
            vendaActual = vendesServei.novaVendaID();
        }
        else throw new VendaJaIniciadaException();
    }

    public void tancarVendaActual() throws VendaNoIniciadaException {
        if (vendaActual != null)  {
            vendaActual.tancar();
            vendesServei.guardarVenda(vendaActual);
        }
        else throw new VendaNoIniciadaException();
    }

    public void finalitzarVendaActual()throws VendaNoIniciadaException{
        if (vendaActual == null) {
            throw new VendaNoIniciadaException();
        }
        vendesServei.guardarVenda(vendaActual);
    }


    //-------------------------
    //SOBRE CATALEG
    //-------------------------


    public boolean potRetornarProducte(int codiVenda, String codiProducte, int unitats) {
        Venda aux = vendesServei.trobaPerCodi(codiVenda);
        if(aux == null) return false;
        return false;
    }

    //------------------------------
    // SOBRE LINIES VENDA
    //-------------------------------
    public void afegirLiniaVenda(String codiProd1, int unitatsProd1) throws ProducteNoExisteixException {
        Producte p = Cataleg.getInstance().getProductePerCodi(codiProd1);
        if (p == null) throw new ProducteNoExisteixException();
        vendaActual.afegeixLinia(p,unitatsProd1);
    }

}
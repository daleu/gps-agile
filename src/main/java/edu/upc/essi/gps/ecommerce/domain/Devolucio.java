package edu.upc.essi.gps.ecommerce.domain;


import edu.upc.essi.gps.domain.Entity;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;
import edu.upc.essi.gps.ecommerce.domain.Producte;

public class Devolucio implements Entity {
    private int idDevolucio;
    private int idVendaCompra;
    private int idVendaRetorn;
    private Producte prodRetornat;
    private int unitatsProducte;
    private String motiu;

    public Devolucio() {
    }

    public String getMotiu() {
        return motiu;
    }

    public void setMotiu(String motiu) {
        this.motiu = motiu;
    }

    public int getIdVendaCompra() {
        return idVendaCompra;
    }

    public void setIdVendaCompra(int idVenda) {
        this.idVendaCompra = idVenda;
    }

    public Producte getProducteRetornat() {
        return prodRetornat;
    }

    public void setProducteRetornat(Producte prodRetornat) {
        this.prodRetornat = prodRetornat;
    }

    public int getUnitatsProducte() {
        return unitatsProducte;
    }

    public void setUnitatsProducte(int unitatsProducte) {
        this.unitatsProducte = unitatsProducte;
    }

    @Override
    public int getId() {
        return idDevolucio;
    }

    public void setID(int ID) {
        this.idDevolucio = ID;
    }

    public void setIdVendaRetorn(int idVendaRetorn) {
        this.idVendaRetorn = idVendaRetorn;
    }

    public int getIdVendaRetorn() {
        return idVendaRetorn;
    }
}
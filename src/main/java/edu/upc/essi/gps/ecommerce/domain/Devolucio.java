package edu.upc.essi.gps.ecommerce.domain;


import edu.upc.essi.gps.domain.Entity;
import edu.upc.essi.gps.ecommerce.repositoris.DevolucionsServei;
import edu.upc.essi.gps.ecommerce.domain.Producte;

public class Devolucio implements Entity {
    private int idDevolucio;
    private int idVenda;
    private Producte prodRetornat;
    private int unitatsProducte;
    private String motiu;

    public Devolucio() {
    }

    public Devolucio(int idVenda,  Producte prodRetornat, int unitatsProducte, String motiu) {
        DevolucionsServei devolucionsServei = new DevolucionsServei();
        this.idVenda = idVenda;
        this.prodRetornat = prodRetornat;
        this.unitatsProducte = unitatsProducte;
        this.motiu = motiu;
    }

    public String getMotiu() {
        return motiu;
    }

    public void setMotiu(String motiu) {
        this.motiu = motiu;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
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
}
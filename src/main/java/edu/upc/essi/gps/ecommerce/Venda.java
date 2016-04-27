package edu.upc.essi.gps.ecommerce;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class LiniaVenda {
    private String nomProducte;
    private double preuUnitat;
    private int quantitat;

    public LiniaVenda() {
    }

    public LiniaVenda(String nomProducte, double preuUnitat, int quantitat) {
        this.nomProducte = nomProducte;
        this.preuUnitat = preuUnitat;
        this.quantitat = quantitat;
    }

    public String getNomProducte() {
        return nomProducte;
    }

    public double getPreuUnitat() {
        return preuUnitat;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public double getPreuTotal() {
        return preuUnitat * quantitat;
    }
}

public class Venda {
    private String message;

    private final List<LiniaVenda> liniesVenda = new LinkedList<>();

    public void afegirLiniaVenda(LiniaVenda liniaVenda) {
        liniesVenda.add(liniaVenda);
    }

    public void afegirLiniaVenda(String nomProducte, double preuUnitat, int quantitat) {
        liniesVenda.add(new LiniaVenda(nomProducte, preuUnitat, quantitat));
    }

    public Venda() {
    }

    public void tancarVenda() {
        if (liniesVenda.isEmpty()) { message = "Venda anul·lada"; }
        else { message = "Venda finalitzada"; }
    }

    public List<LiniaVenda> getLiniesVenda() {
        return Collections.unmodifiableList(liniesVenda);
    }

    public double getTotal() {
        double res = 0;
        for(LiniaVenda l : liniesVenda){
            res += l.getPreuTotal();
        }
        return res;
    }

    public String getMessage () {return message; }

    public boolean isEmpty() {
        return liniesVenda.isEmpty();
    }
}
package edu.upc.essi.gps.ecommerce;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class LiniaVenda {
    private String nomProducte;
    private double preuUnitat;
    private int quantitat;

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

    public void sumQuantitat(int quantitat) { this.quantitat += quantitat; }
}

public class Venda {
    private String message;
    private double pagament;

    private final List<LiniaVenda> liniesVenda = new LinkedList<>();

    public void afegirLiniaVenda(LiniaVenda liniaVenda) {
        for (LiniaVenda lv : liniesVenda) {
            if (liniaVenda.getNomProducte() == lv.getNomProducte()) {
                lv.sumQuantitat(liniaVenda.getQuantitat());
                return;
            }
        }
        liniesVenda.add(liniaVenda);
    }

    public Venda() {
    }

    public void tancarVenda(boolean anulacio) {
        if (anulacio) { message = "Venda anul·lada"; }
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

    public String getMessage () { return message; }

    public void setPagament (double cash) { pagament = cash; }

    public double getCanvi () { return pagament - getTotal(); }

    public String getTicket() {
        String ticket = "";
        DecimalFormat df = new DecimalFormat("####0.00");
        for(LiniaVenda l : liniesVenda){
            ticket = ticket.concat(Integer.toString(l.getQuantitat())); ticket = ticket.concat("|");
            ticket = ticket.concat(l.getNomProducte()); ticket = ticket.concat("|");
            ticket = ticket.concat(df.format(l.getPreuUnitat())); ticket = ticket.concat("|");
            ticket = ticket.concat(df.format(l.getPreuTotal()));
            ticket = ticket.concat(" - ");
        }
        ticket = ticket.concat("Preu total: "); ticket = ticket.concat((df.format(getTotal())));
        ticket = ticket.concat(" - ");
        ticket = ticket.concat("Pagament: ");ticket = ticket.concat(df.format(pagament));
        ticket = ticket.concat(" - ");
        ticket = ticket.concat("Canvi: ");ticket = ticket.concat(df.format(getCanvi()));
        return ticket;
    }

    public boolean isEmpty() {
        return liniesVenda.isEmpty();
    }
}

package edu.upc.essi.gps.ecommerce;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Venda {
    private int numVenda;
    private String message;
    private double pagament;
    private String ticket;
    private double[] iva = {0.1, 0.21}; //0.1 llaminadures, 0.21 joguines

    private final List<LiniaVenda> liniesVenda = new LinkedList<>();

    public Venda(int numVenda) {
        this.numVenda = numVenda;
        ticket = "";
    }

    public void afegirLiniaVenda(LiniaVenda liniaVenda) {
        for (LiniaVenda lv : liniesVenda) {
            if (liniaVenda.getNomProducte() == lv.getNomProducte()) {
                lv.sumQuantitat(liniaVenda.getQuantitat());
                return;
            }
        }
        liniesVenda.add(liniaVenda);
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

    public double getTotalIva() {
        double res = 0;
        for(LiniaVenda l : liniesVenda){
            res += l.getPreuTotalAmbIva();
        }
        return Math.round(res*100.0)/100.0;
    }

    public String getMessage () { return message; }

    public void setPagament (double cash) { pagament = cash; }

    public double getCanvi () { return pagament - getTotal(); }

    public void crearTicket () {
        for(LiniaVenda l : liniesVenda){
            ticket = ticket.concat(Integer.toString(l.getQuantitat())); ticket = ticket.concat("|");
            ticket = ticket.concat(l.getNomProducte()); ticket = ticket.concat("|");
            ticket = ticket.concat(Double.toString(Math.round(l.getPreuUnitat()*100.0)/100.0)); ticket = ticket.concat("|");
            ticket = ticket.concat(Double.toString(Math.round(l.getPreuTotal()*100.0)/100.0));
            ticket = ticket.concat(" - ");
        }
        ticket = ticket.concat("Preu total: "); ticket = ticket.concat(Double.toString(Math.round(getTotal()*100.0)/100.0));
        ticket = ticket.concat(" - ");
        ticket = ticket.concat("Pagament: ");ticket = ticket.concat(Double.toString(Math.round(pagament*100.0)/100.0));
        ticket = ticket.concat(" - ");
        ticket = ticket.concat("Canvi: ");ticket = ticket.concat(Double.toString(Math.round(getCanvi()*100.0)/100.0));
    }
    public String getTicket() {
        return ticket;
    }

    public boolean isEmpty() {
        return liniesVenda.isEmpty();
    }
}

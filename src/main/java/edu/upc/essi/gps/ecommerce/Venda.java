package edu.upc.essi.gps.ecommerce;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class TerminalVenda {
    private String nomEmpleat;
    private String nomTerminal;
    private String nomPoblacio;

    public TerminalVenda(String nomEmpleat, String nomTerminal, String nomPoblacio) {
        this.nomEmpleat = nomEmpleat;
        this.nomTerminal = nomTerminal;
        this.nomPoblacio = nomPoblacio;
    }
}

class LiniaVenda {
    private String nomProducte;
    private double preuUnitat;
    private double preuUnitatMesIva;
    private int quantitat;

    public LiniaVenda(String nomProducte, double preuUnitat, int quantitat) {
        this.nomProducte = nomProducte;
        this.preuUnitat = preuUnitat;
        this.quantitat = quantitat;
    }

    public LiniaVenda(String nomProducte, double preuUnitat, int quantitat, int iva) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.nomProducte = nomProducte;
        this.preuUnitat = preuUnitat;
        this.quantitat = quantitat;
        if (iva == 21) this.preuUnitatMesIva = preuUnitat + preuUnitat*0.21;
        else this.preuUnitatMesIva = preuUnitat + preuUnitat*0.04;
        this.preuUnitatMesIva = Math.round(preuUnitatMesIva*100.0)/100.0;
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

    public double getPreuTotalAmbIva() {
        return preuUnitatMesIva * quantitat;
    }

    public void sumQuantitat(int quantitat) { this.quantitat += quantitat; }
}

public class Venda {
    private String message;
    private double pagament;
    private double[] iva = {0.1, 0.21}; //0.1 llaminadures, 0.21 joguines

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

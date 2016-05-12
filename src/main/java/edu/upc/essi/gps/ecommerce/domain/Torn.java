package edu.upc.essi.gps.ecommerce.domain;


import edu.upc.essi.gps.domain.Entity;

public class Torn implements Entity{
    private String nomEmpleat;
    private String nomBotiga;
    private Double efectiuInici;
    private Double efectiuFi;
    private Double dinersEnCaixa;
    private boolean finalitzat = false;
    private int id;

    public Torn(String empleatActual) {
        this.nomEmpleat = empleatActual;
    }

    public Torn(String empleatActual, String nomBotiga)
    {
        this.nomEmpleat = empleatActual;
        this.nomBotiga = nomBotiga;
    }

    public Torn(int id, String nomEmpleat, String nomBotiga) {
        this.id = id;
        this.nomEmpleat = nomEmpleat;
        this.nomBotiga = nomBotiga;
    }

    public String getNomEmpleat() {
        return nomEmpleat;
    }

    public String getNomBotiga() {
        return nomBotiga;
    }

    public void setNomBotiga(String nomBotiga) {
        this.nomBotiga = nomBotiga;
    }

    public void setNomEmpleat(String nomEmpleat) {
        this.nomEmpleat = nomEmpleat;
    }

    public void setEfectiuInici(Double efectiuInici) { this.efectiuInici = efectiuInici; }

    public Double getEfectiuInici() {return this.efectiuInici; }

    public void finalitza() {
        finalitzat = true;
    }

    public void cancelarFinalitzacio() {
        finalitzat = false;
    }

    public boolean getFinalitzat() { return finalitzat; }


    public Double getEfectiuFi() {
        return efectiuFi;
    }

    public void setEfectiuFi(Double efectiuFi) { this.efectiuFi = efectiuFi; }

    public Double getDinersEnCaixa() {
        return dinersEnCaixa;
    }

    public void setDinersEnCaixa(Double dinersEnCaixa) { this.dinersEnCaixa = dinersEnCaixa; }

    public void incrementDinersEnCaixa(Double increment) { this.dinersEnCaixa += increment; }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
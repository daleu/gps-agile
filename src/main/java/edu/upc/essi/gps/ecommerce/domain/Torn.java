package edu.upc.essi.gps.ecommerce.domain;


public class Torn {
    private String nomEmpleat;
    private String nomBotiga;

    public Torn(String empleatActual) {
        this.nomEmpleat = empleatActual;
    }

    public Torn(String empleatActual, String nomBotiga)
    {
        this.nomEmpleat = empleatActual;
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
}
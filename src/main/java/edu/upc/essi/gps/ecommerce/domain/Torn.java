package edu.upc.essi.gps.ecommerce.domain;


public class Torn {
    private String empleatActual;
    private String botigaActual;

    public Torn(String empleatAcutal, String botigaActual)
    {
        this.empleatActual = empleatAcutal;
        this.botigaActual = botigaActual;
    }

    public String getEmpleatActual() {
        return empleatActual;
    }

    public String getBotigaActual() {
        return botigaActual;
    }
}
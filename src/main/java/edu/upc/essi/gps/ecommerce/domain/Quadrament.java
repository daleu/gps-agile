package edu.upc.essi.gps.ecommerce.domain;

import java.util.ArrayList;

/**
 * Created by aleue on 11/5/2016.
 */
public class Quadrament {
    private double efectiuFinal;
    private double efectiuInicial;
    private Integer idTorn;

    public Quadrament(double efectiuInicial, double efectiuFinal, Integer idTorn) {
        this.efectiuInicial = efectiuInicial;
        this.efectiuFinal = efectiuFinal;
        this.idTorn = idTorn;
    }
}

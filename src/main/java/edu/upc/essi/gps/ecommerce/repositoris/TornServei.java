package edu.upc.essi.gps.ecommerce.repositoris;
import java.util.List;



public class TornServei {
    private TornRepositori tornRepositori;

    public TornServei() {
        this.tornRepositori = new TornRepositori();
    }

    public Integer assignarIdTorn(){ //ID auto
        int id = tornRepositori.newId();
        return id;
    }
}
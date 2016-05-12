package edu.upc.essi.gps.ecommerce.repositoris;
import edu.upc.essi.gps.ecommerce.domain.Torn;
import java.util.List;
public class TornServei {

    private TornRepositori tornRepositori;

    public TornServei() {
        this.tornRepositori = new TornRepositori();
    }

    public Integer assignarIdTorn(){ //ID auto
        int id = tornRepositori.newId();
        id=id+10;
        System.out.println(id+" zca ");
        return id;
    }

    public void guardarTorn(Torn tor){
        tornRepositori.checkInsert(tor);
        //tornRepositori.insert(tor);
    }

    public List<Torn> llistarTorns() {
        return tornRepositori.list();
    }
}
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
        return id;
    }

    public void guardarTorn(Torn tor){
        tornRepositori.insert(tor);
    }

    public List<Torn> llistarTorns() {
        return tornRepositori.list();
    }

    public Torn getUltimTorn() {
        List<Torn> torns = llistarTorns();
        Torn aux = torns.get(0);
        for (Torn t: torns) {
            if (aux.getId() < t.getId()) {
                aux = t;
            }
        }
        return aux;
    }
}
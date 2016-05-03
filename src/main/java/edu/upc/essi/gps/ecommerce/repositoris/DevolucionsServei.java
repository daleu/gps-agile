package edu.upc.essi.gps.ecommerce.repositoris;

import edu.upc.essi.gps.ecommerce.domain.Devolucio;
import edu.upc.essi.gps.ecommerce.domain.LiniaVenda;
import edu.upc.essi.gps.ecommerce.domain.Venda;

import java.util.List;
public class DevolucionsServei {

    private DevolucionsRepositori devolucionsRepositori;

    public DevolucionsServei() {
        this.devolucionsRepositori = new DevolucionsRepositori();
    }

    public Devolucio novaDevolucio(){ //ID auto
        int id = devolucionsRepositori.newId();
        return novaDevolucioAmbID(id);
    }

    public Devolucio  novaDevolucioAmbID(int id){ //ID manual
        Devolucio result = new Devolucio();
        result.setID(id);
        devolucionsRepositori.checkInsert(result);
        return result;
    }

    public void guardarDevolucio(Devolucio dev){
        devolucionsRepositori.checkInsert(dev);
        devolucionsRepositori.insert(dev);
    }

    public List<Devolucio> llistarVendes(){
        return devolucionsRepositori.list();
    }

    public Devolucio trobaPerCodi(int id) {
        return devolucionsRepositori.trobarDevolucio(id);
    }

    public Devolucio trobarPerParametres(int expectedIdVenda, String expectedCodiBarres, int i) {
        return devolucionsRepositori.trobarDevolucioPerParametres(expectedIdVenda,expectedCodiBarres);
    }
}
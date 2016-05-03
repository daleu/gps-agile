package edu.upc.essi.gps.ecommerce.repositoris;

import edu.upc.essi.gps.ecommerce.domain.LiniaVenda;
import edu.upc.essi.gps.ecommerce.domain.Venda;

import java.util.List;
public class VendesServei {

    private VendesRepositori vendesRepositori;

    public VendesServei() {
        this.vendesRepositori = new VendesRepositori();
    }

    public Venda novaVenda(){ //ID auto
        int id = vendesRepositori.newId();
        Venda result = new Venda(id);
        return result;
    }

    public Venda novaVendaAmbID(int id){ //ID manual
        Venda result = new Venda(id);
        vendesRepositori.checkInsert(result);
        return result;
    }

    public void guardarVenda(Venda ven){
        vendesRepositori.checkInsert(ven);
        vendesRepositori.insert(ven);
    }

    public List<Venda> llistarVendes(){
        return vendesRepositori.list();
    }

    public Venda trobaPerCodi(int id) {
        return vendesRepositori.trobarVenda(id);
    }

    public void indicarDevolucio(int idVenda, String codiBarres, int unitatsProd) throws Exception {
            Venda v = trobaPerCodi(idVenda);
            vendesRepositori.delete(v);
            v.modificarLinia(codiBarres,unitatsProd);
            vendesRepositori.checkInsert(v);
            vendesRepositori.insert(v);
    }
}

package edu.upc.essi.gps.ecommerce.repositoris;

import edu.upc.essi.gps.ecommerce.domain.Devolucio;
import edu.upc.essi.gps.utils.Repository;

import java.util.List;

public class DevolucionsRepositori extends Repository<Devolucio>{

    public Devolucio trobarDevolucio(final long id){
        return find((p) -> p.getId() == id);
    }

    @Override
    protected void checkInsert(Devolucio entity) throws RuntimeException {
        if(trobarDevolucio(entity.getId())!=null)
            throw new IllegalArgumentException("Ja existeix una venda amb aquest nom");
    }


    public Devolucio trobarDevolucioPerParametres(int expectedIdVenda, String expectedCodiBarres) { //FALTA PULIR INTRODUINT UNITATS
        List<Devolucio> devolucions = list();
        for(Devolucio d: devolucions) {
            if((d.getIdVendaCompra() == expectedIdVenda) && (d.getProducteRetornat().getCodiBarres() == expectedCodiBarres)) {
                return d;
            }
        }
        return null;
    }


}
package edu.upc.essi.gps.ecommerce.repositoris;

import edu.upc.essi.gps.ecommerce.domain.Venda;
import edu.upc.essi.gps.utils.Repository;

public class VendesRepositori extends Repository<Venda>{

    public Venda trobarVenda(final long id){
        return find((p) -> p.getId() == id);
    }

    @Override
    protected void checkInsert(Venda entity) throws RuntimeException {
        if(trobarVenda(entity.getId())!=null)
            throw new IllegalArgumentException("Ja existeix una venda amb aquest nom");
    }

}
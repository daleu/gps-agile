package edu.upc.essi.gps.ecommerce.repositoris;

import edu.upc.essi.gps.ecommerce.domain.Venda;
import edu.upc.essi.gps.ecommerce.domain.descomptes.Descompte;
import edu.upc.essi.gps.utils.Repository;

public class DescomptesRepositori extends Repository<Descompte>{

    public Descompte trobarDescompte(final long id){
        return find((p) -> p.getId() == id);
    }

    @Override
    protected void checkInsert(Descompte entity) throws RuntimeException {
        if(trobarDescompte(entity.getId())!=null)
            throw new IllegalArgumentException("Ja existeix una descompte amb aquest id");
    }

}
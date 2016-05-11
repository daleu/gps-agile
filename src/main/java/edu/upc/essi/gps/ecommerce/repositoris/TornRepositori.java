package edu.upc.essi.gps.ecommerce.repositoris;
import edu.upc.essi.gps.ecommerce.domain.Devolucio;
import edu.upc.essi.gps.ecommerce.domain.Torn;
import edu.upc.essi.gps.utils.Repository;
import java.util.List;


public class TornRepositori extends Repository<Torn> {

        public Torn trobarTorn(final long id){
            return find((p) -> p.getId() == id);
        }

        @Override
        protected void checkInsert(Torn entity) throws RuntimeException {
            if(trobarTorn(entity.getId())!=null)
                throw new IllegalArgumentException("Ja existeix una venda amb aquest nom");
        }
}

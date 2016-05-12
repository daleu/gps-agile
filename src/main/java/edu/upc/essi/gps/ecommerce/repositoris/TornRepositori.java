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
        protected void checkInsert(Torn torn) throws RuntimeException {
            System.out.println("       ? "+trobarTorn(torn.getId()));
            if(trobarTorn(torn.getId())!=null)
                throw new IllegalArgumentException("Ja existeix un torn amb aquest nom");
        }
}

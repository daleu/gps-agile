package edu.upc.essi.gps.ecommerce.domain.ofertes;

import java.util.Calendar;

/**
 * Created by Jan on 01/06/2016.
 */

public class OfertaPercentatge extends Oferta {

    private int percentatge;

    public OfertaPercentatge(int id, int percentatge, Calendar dataInici, Calendar dataFinal) {
        super(id, dataInici, dataFinal);
        this.percentatge = percentatge;
    }

    public int getPercentatge() { return this.percentatge; }
}

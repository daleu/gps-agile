package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public class DescomptePercentatge extends Descompte {

    public DescomptePercentatge(int id, double descompte, Calendar dataCaducitat) {
        super(id, descompte, dataCaducitat);
    }
}

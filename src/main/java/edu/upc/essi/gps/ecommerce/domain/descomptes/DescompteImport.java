package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public class DescompteImport extends Descompte {

    private double importMinim;

    public DescompteImport(int id, double descompte, Calendar dataCaducitat) {
        super(id, descompte, dataCaducitat);
        importMinim = descompte * 5;
    }

    public double getImportMinim() {
        return importMinim;
    }
}

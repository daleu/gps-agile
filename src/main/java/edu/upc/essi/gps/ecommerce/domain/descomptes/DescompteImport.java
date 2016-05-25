package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public class DescompteImport extends Descompte {

    private double importMinim;

    public DescompteImport(String codiDeBarres, double descompte, Calendar dataCaducitat, double importMinim) {
        super(codiDeBarres, descompte, dataCaducitat);
        this.importMinim = importMinim;
    }

    public double getImportMinim() {
        return importMinim;
    }
}

package edu.upc.essi.gps.ecommerce.domain.descomptes;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public class DescompteImport extends Descompte {

    private double importMinim;

    public DescompteImport(int id, double descompte, Calendar dataCaducitat, double importMinim) {
        super(id, descompte, dataCaducitat);
        this.importMinim = importMinim;
    }

    public double getImportMinim() {
        return importMinim;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double calcularPreu(double preuFinal) {
        return (preuFinal - descompte);
    }
}

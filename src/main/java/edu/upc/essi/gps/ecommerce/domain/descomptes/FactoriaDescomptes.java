package edu.upc.essi.gps.ecommerce.domain.descomptes;

/**
 * Created by edu on 19/05/16.
 */
public class FactoriaDescomptes {
    private FactoriaDescomptes instance;
    private int id;

    private FactoriaDescomptes() {
        id = 1;
    }

    public FactoriaDescomptes getInstance() {
        if (instance == null) instance = new FactoriaDescomptes();
        return instance;
    }

    public Descompte getDescomeptePerPercentatge() {
        return new DescomptePercentatge(id++);
    }

    public Descompte getDescomptePerImport() {
        return new DescompteImport(id++);
    }
}

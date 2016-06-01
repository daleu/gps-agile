package edu.upc.essi.gps.ecommerce.domain.ofertes;

/**
 * Created by Jan on 01/06/2016.
 */
import edu.upc.essi.gps.domain.Entity;

import java.util.Calendar;

/**
 * Created by edu on 19/05/16.
 */
public abstract class Oferta implements Entity {
    protected int id;
    protected Calendar dataInici;
    protected Calendar dataFinal;

    protected Oferta(int id, Calendar dataInici, Calendar dataFinal) {
        this.id = id;
        this.dataInici = dataInici;
        this.dataFinal = dataFinal;
    }

    public int getId() { return id; }

    public void setDataInici(Calendar dInici) { this.dataInici = dInici; }

    public Calendar getDataInici() { return dataInici; }

    public void setDataFinal(Calendar dFinal) { this.dataFinal = dFinal; }

    public Calendar getDataFinal() { return dataFinal; }
}


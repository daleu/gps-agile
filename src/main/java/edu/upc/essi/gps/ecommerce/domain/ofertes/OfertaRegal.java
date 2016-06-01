package edu.upc.essi.gps.ecommerce.domain.ofertes;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Jan on 01/06/2016.
 */

public class OfertaRegal extends Oferta {

    private String idRegal;
    private String quantitat;

    public OfertaRegal(int id, String quantitat, String idRegal, Calendar dataInici, Calendar dataFinal) {
        super(id, dataInici, dataFinal);
        this.idRegal = idRegal;
        this.quantitat = quantitat;
    }

    public String getIdRegal() { return this.idRegal; }

    public String getQuantitat() { return this.quantitat; }
}
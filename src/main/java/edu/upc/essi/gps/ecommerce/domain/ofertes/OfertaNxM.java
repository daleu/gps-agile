package edu.upc.essi.gps.ecommerce.domain.ofertes;

import java.util.Calendar;

/**
 * Created by Jan on 01/06/2016.
 */

public class OfertaNxM extends Oferta {

    private int N;
    private int M;

    public OfertaNxM(int id, int N, int M, Calendar dataInici, Calendar dataFinal) {
        super(id, dataInici, dataFinal);
        this.N = N;
        this.M = M;
    }

    public int getN() { return N; }

    public int getM() { return M; }
}


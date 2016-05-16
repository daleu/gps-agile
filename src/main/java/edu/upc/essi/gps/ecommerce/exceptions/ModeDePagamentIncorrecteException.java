package edu.upc.essi.gps.ecommerce.exceptions;

/**
 * Created by nailek on 16/05/2016.
 */
public class ModeDePagamentIncorrecteException extends  Exception {
    @Override
    public String getMessage() {
        return "Error: El mode de pagament és incorrecte.";
    }
}

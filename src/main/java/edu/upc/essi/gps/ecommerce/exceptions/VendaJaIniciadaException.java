package edu.upc.essi.gps.ecommerce.exceptions;

/**
 * Created by eduard.maura.i on 29/04/2016.
 */
public class VendaJaIniciadaException extends Exception {
    @Override
    public String getMessage() {
        return "Error: Ja hi ha una venda iniciada";
    }
}

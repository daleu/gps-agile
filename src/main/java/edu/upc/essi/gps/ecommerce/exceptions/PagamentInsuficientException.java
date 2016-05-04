package edu.upc.essi.gps.ecommerce.exceptions;

/**
 * Created by nailek on 04/05/2016.
 */
public class PagamentInsuficientException extends Exception {

    @Override
    public String getMessage() {
        return "Error: El pagament és insuficient.";
    }
}

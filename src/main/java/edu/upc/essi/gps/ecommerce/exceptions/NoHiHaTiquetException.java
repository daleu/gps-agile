package edu.upc.essi.gps.ecommerce.exceptions;

/**
 * Created by Nailek on 04/05/2016.
 */
public class NoHiHaTiquetException extends Exception{
    @Override
    public String getMessage() {
        return "Error: No hi ha cap excepció.";
    }
}

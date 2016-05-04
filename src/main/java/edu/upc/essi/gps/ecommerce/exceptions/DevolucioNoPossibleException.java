package edu.upc.essi.gps.ecommerce.exceptions;

public class DevolucioNoPossibleException extends Exception{
    @Override
    public String getMessage() {
        return "Error: No es possible realitzar aquesta devolució";
    }
}

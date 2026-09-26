package co.edu.uniquindio.lenguajecafetero.exception;

public class MatriculaInvalidaException extends Exception {
    public MatriculaInvalidaException(String mensaje) {
        super(mensaje);
    }

    public MatriculaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

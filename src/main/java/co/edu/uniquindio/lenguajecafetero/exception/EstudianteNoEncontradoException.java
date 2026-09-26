package co.edu.uniquindio.lenguajecafetero.exception;

public class EstudianteNoEncontradoException extends Exception {
    public EstudianteNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public EstudianteNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

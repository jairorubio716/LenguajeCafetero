package co.edu.uniquindio.lenguajecafetero.model;

public class CursoIntensivo extends Curso {
    private static final double RECARGO_INTENSIVO = 1.30;

    public CursoIntensivo(String codigo, String nombre, Idioma idioma, String descripcion,
                          double valorMensual, int duracionMeses) {
        super(codigo, nombre, idioma, descripcion, valorMensual, duracionMeses);
    }

    public CursoIntensivo(CursoIntensivo curso) {
        super(curso);
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses() * RECARGO_INTENSIVO;
    }

    @Override
    public Curso clonar() {
        return new CursoIntensivo(this);
    }
}
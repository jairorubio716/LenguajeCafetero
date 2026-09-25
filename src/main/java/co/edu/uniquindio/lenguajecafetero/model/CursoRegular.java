package co.edu.uniquindio.lenguajecafetero.model;

public class CursoRegular extends Curso {
    public CursoRegular(String codigo, String nombre, Idioma idioma, String descripcion,
                        double valorMensual, int duracionMeses) {
        super(codigo, nombre, idioma, descripcion, valorMensual, duracionMeses);
    }

    public CursoRegular(CursoRegular curso) {
        super(curso);
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }

    @Override
    public Curso clonar() {
        return new CursoRegular(this);
    }
}
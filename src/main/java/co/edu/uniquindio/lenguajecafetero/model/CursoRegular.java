package co.edu.uniquindio.lenguajecafetero.model;

public class CursoRegular extends Curso {
    public CursoRegular(String codigo, String nombre, double precioBase, int duracionMeses) {
        super(codigo, nombre, precioBase, duracionMeses);
    }

    public CursoRegular(CursoRegular curso) {
        super(curso);
    }

    @Override
    public double calcularValor() {
        return getPrecioBase() * getDuracionMeses();
    }

    @Override
    public Curso clonar() {
        return new CursoRegular(this);
    }
}
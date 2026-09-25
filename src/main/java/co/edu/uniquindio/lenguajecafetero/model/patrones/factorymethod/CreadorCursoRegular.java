package co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod;

import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.CursoRegular;

public class CreadorCursoRegular extends CreadorDeCursos {
    private final String codigo;
    private final String nombre;
    private final double precioBase;
    private final int duracionMeses;

    public CreadorCursoRegular(String codigo, String nombre, double precioBase, int duracionMeses) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.duracionMeses = duracionMeses;
    }

    @Override
    public Curso crearCurso() {
        return new CursoRegular(codigo, nombre, precioBase, duracionMeses);
    }
}
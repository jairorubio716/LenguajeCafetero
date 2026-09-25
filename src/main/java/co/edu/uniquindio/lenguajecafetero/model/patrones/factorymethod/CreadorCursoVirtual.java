package co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod;

import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.CursoVirtual;

public class CreadorCursoVirtual extends CreadorDeCursos {
    private final String codigo;
    private final String nombre;
    private final double precioBase;
    private final int duracionMeses;
    private final boolean plataformaVirtual;

    public CreadorCursoVirtual(String codigo, String nombre, double precioBase,
                               int duracionMeses, boolean plataformaVirtual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.duracionMeses = duracionMeses;
        this.plataformaVirtual = plataformaVirtual;
    }

    @Override
    public Curso crearCurso() {
        return new CursoVirtual(codigo, nombre, precioBase, duracionMeses, plataformaVirtual);
    }
}
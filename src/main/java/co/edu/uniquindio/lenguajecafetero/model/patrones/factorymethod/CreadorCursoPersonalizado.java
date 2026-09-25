package co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod;

import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.CursoPersonalizado;

public class CreadorCursoPersonalizado extends CreadorDeCursos {
    private final String codigo;
    private final String nombre;
    private final double precioBase;
    private final int duracionMeses;
    private final int sesionesPorMes;
    private final String nivelReferencia;
    private final String objetivosEstudiante;

    public CreadorCursoPersonalizado(String codigo, String nombre, double precioBase, int duracionMeses,
                                     int sesionesPorMes, String nivelReferencia, String objetivosEstudiante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.duracionMeses = duracionMeses;
        this.sesionesPorMes = sesionesPorMes;
        this.nivelReferencia = nivelReferencia;
        this.objetivosEstudiante = objetivosEstudiante;
    }

    @Override
    public Curso crearCurso() {
        return new CursoPersonalizado(codigo, nombre, precioBase, duracionMeses,
                sesionesPorMes, nivelReferencia, objetivosEstudiante);
    }
}
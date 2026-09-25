package co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod;

import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.CursoPersonalizado;
import co.edu.uniquindio.lenguajecafetero.model.Idioma;
import co.edu.uniquindio.lenguajecafetero.model.NivelIdioma;

public class CreadorCursoPersonalizado extends CreadorDeCursos {
    private final String codigo;
    private final String nombre;
    private final Idioma idioma;
    private final String descripcion;
    private final double valorMensual;
    private final int duracionMeses;
    private final int sesionesPorMes;
    private final NivelIdioma nivelReferencia;
    private final String objetivosEstudiante;

    public CreadorCursoPersonalizado(String codigo, String nombre, Idioma idioma, String descripcion,
                                     double valorMensual, int duracionMeses,
                                     int sesionesPorMes, NivelIdioma nivelReferencia, String objetivosEstudiante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.valorMensual = valorMensual;
        this.duracionMeses = duracionMeses;
        this.sesionesPorMes = sesionesPorMes;
        this.nivelReferencia = nivelReferencia;
        this.objetivosEstudiante = objetivosEstudiante;
    }

    @Override
    public Curso crearCurso() {
        return new CursoPersonalizado(codigo, nombre, idioma, descripcion, valorMensual, duracionMeses,
                sesionesPorMes, nivelReferencia, objetivosEstudiante);
    }
}
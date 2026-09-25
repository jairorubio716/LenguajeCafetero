package co.edu.uniquindio.lenguajecafetero.model.patrones.factorymethod;

import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.CursoIntensivo;
import co.edu.uniquindio.lenguajecafetero.model.Idioma;

public class CreadorCursoIntensivo extends CreadorDeCursos {
    private final String codigo;
    private final String nombre;
    private final Idioma idioma;
    private final String descripcion;
    private final double valorMensual;
    private final int duracionMeses;

    public CreadorCursoIntensivo(String codigo, String nombre, Idioma idioma, String descripcion,
                                 double valorMensual, int duracionMeses) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.valorMensual = valorMensual;
        this.duracionMeses = duracionMeses;
    }

    @Override
    public Curso crearCurso() {
        return new CursoIntensivo(codigo, nombre, idioma, descripcion, valorMensual, duracionMeses);
    }
}
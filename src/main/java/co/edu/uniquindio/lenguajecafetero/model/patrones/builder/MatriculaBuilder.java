package co.edu.uniquindio.lenguajecafetero.model.patrones.builder;

import co.edu.uniquindio.lenguajecafetero.exception.MatriculaInvalidaException;
import co.edu.uniquindio.lenguajecafetero.model.Curso;
import co.edu.uniquindio.lenguajecafetero.model.Estudiante;
import co.edu.uniquindio.lenguajecafetero.model.Matricula;
import co.edu.uniquindio.lenguajecafetero.model.ServicioAdicional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaBuilder {
    private Estudiante estudiante;
    private Curso curso;
    private LocalDate fecha;
    private int duracionContratada;
    private double descuentoAplicado;
    private List<ServicioAdicional> serviciosIncluidos;

    public MatriculaBuilder() {
        this.fecha = LocalDate.now();
        this.duracionContratada = 3;
        this.descuentoAplicado = 0;
        this.serviciosIncluidos = new ArrayList<>();
    }

    public MatriculaBuilder conEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        return this;
    }

    public MatriculaBuilder conCurso(Curso curso) {
        this.curso = curso;
        this.duracionContratada = curso.getDuracionMeses();
        return this;
    }

    public MatriculaBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public MatriculaBuilder conDuracion(int meses) {
        this.duracionContratada = meses;
        return this;
    }

    public MatriculaBuilder agregarServicio(ServicioAdicional servicio) {
        this.serviciosIncluidos.add(servicio);
        return this;
    }

    public MatriculaBuilder aplicarDescuento(double porcentaje) {
        this.descuentoAplicado = porcentaje;
        return this;
    }

    public Matricula build() throws MatriculaInvalidaException {
        if (estudiante == null || curso == null) {
            throw new MatriculaInvalidaException("La matricula requiere estudiante y curso.");
        }
        return new Matricula("", fecha, estudiante, curso,
                duracionContratada, descuentoAplicado, serviciosIncluidos);
    }
}

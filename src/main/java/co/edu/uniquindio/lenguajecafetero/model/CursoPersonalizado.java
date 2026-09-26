package co.edu.uniquindio.lenguajecafetero.model;

public class CursoPersonalizado extends Curso {
    private static final double VALOR_POR_SESION = 20000.0;

    private int sesionesPorMes;
    private NivelIdioma nivelReferencia;
    private String objetivosEstudiante;

    public CursoPersonalizado(String codigo, String nombre, Idioma idioma, String descripcion,
                              double valorMensual, int duracionMeses,
                              int sesionesPorMes, NivelIdioma nivelReferencia, String objetivosEstudiante) {
        super(codigo, nombre, idioma, descripcion, valorMensual, duracionMeses);
        this.sesionesPorMes = sesionesPorMes;
        this.nivelReferencia = nivelReferencia;
        this.objetivosEstudiante = objetivosEstudiante;
    }

    public CursoPersonalizado(CursoPersonalizado curso) {
        super(curso);
        this.sesionesPorMes = curso.sesionesPorMes;
        this.nivelReferencia = curso.nivelReferencia;
        this.objetivosEstudiante = curso.objetivosEstudiante;
    }

    @Override
    public double calcularValor() {
        double valorMensualCurso = getValorMensual() + (sesionesPorMes * VALOR_POR_SESION);
        return valorMensualCurso * getDuracionMeses();
    }

    @Override
    public Curso clonar() {
        return new CursoPersonalizado(this);
    }

    @Override
    public String toString() {
        return getCodigo() + " - " + getNombre() + " - Personalizado - " + nivelReferencia + " - " + getEstado();
    }

    public int getSesionesPorMes() {
        return sesionesPorMes;
    }

    public void setSesionesPorMes(int sesionesPorMes) {
        this.sesionesPorMes = sesionesPorMes;
    }

    public NivelIdioma getNivelReferencia() {
        return nivelReferencia;
    }

    public void setNivelReferencia(NivelIdioma nivelReferencia) {
        this.nivelReferencia = nivelReferencia;
    }

    public String getObjetivosEstudiante() {
        return objetivosEstudiante;
    }

    public void setObjetivosEstudiante(String objetivosEstudiante) {
        this.objetivosEstudiante = objetivosEstudiante;
    }
}
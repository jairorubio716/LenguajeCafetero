package co.edu.uniquindio.lenguajecafetero.model;

public class Beneficio {
    private TipoBeneficio tipo;
    private String nombre;
    private String descripcion;

    public Beneficio(TipoBeneficio tipo, String nombre, String descripcion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public TipoBeneficio getTipo() {
        return tipo;
    }

    public void setTipo(TipoBeneficio tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
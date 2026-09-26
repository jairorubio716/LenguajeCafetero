package co.edu.uniquindio.lenguajecafetero.model;

public class ServicioAdicional {
    private TipoServicio tipo;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;

    public ServicioAdicional(TipoServicio tipo, String codigo, String nombre,
                             String descripcion, double precio, boolean disponible) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - $" + precio + " - disponible: " + disponible;
    }

    public TipoServicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoServicio tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
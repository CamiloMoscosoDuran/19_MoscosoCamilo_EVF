package pe.edu.vallegrande.model;

import java.sql.Date;

public class Incidencia {
    private int id;
    private String tipo;
    private String aula;
    private Date fecha;
    private String estado;
    private String descripcion;

    // Constructor vacío
    public Incidencia() {}

    // Constructor con todos los campos (para Modificar)
    public Incidencia(int id, String tipo, String aula, Date fecha, String estado, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.aula = aula;
        this.fecha = fecha;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    // Constructor sin ID (para Registrar, ya que es AUTO_INCREMENT)
    public Incidencia(String tipo, String aula, Date fecha, String estado, String descripcion) {
        this.tipo = tipo;
        this.aula = aula;
        this.fecha = fecha;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    // --- Getters y Setters (Encapsulamiento) ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getAula() { return aula; }
    public void setAula(String aula) { this.aula = aula; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
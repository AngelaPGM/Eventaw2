package es.taw.eventaw.dto;

import java.sql.Date;

public class AnalisisDTO {
    private Integer id;
    private String nombre;
    private Date fechamayor;
    private Date fechamenor;
    private Double preciomayor;
    private Double preciomenor;
    private String sexo;
    private Date nacimientomayor;
    private Date nacimientomenor;

    public AnalisisDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechamayor() {
        return fechamayor;
    }

    public void setFechamayor(Date fechamayor) {
        this.fechamayor = fechamayor;
    }

    public Date getFechamenor() {
        return fechamenor;
    }

    public void setFechamenor(Date fechamenor) {
        this.fechamenor = fechamenor;
    }

    public Double getPreciomayor() {
        return preciomayor;
    }

    public void setPreciomayor(Double preciomayor) {
        this.preciomayor = preciomayor;
    }

    public Double getPreciomenor() {
        return preciomenor;
    }

    public void setPreciomenor(Double preciomenor) {
        this.preciomenor = preciomenor;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getNacimientomayor() {
        return nacimientomayor;
    }

    public void setNacimientomayor(Date nacimientomayor) {
        this.nacimientomayor = nacimientomayor;
    }

    public Date getNacimientomenor() {
        return nacimientomenor;
    }

    public void setNacimientomenor(Date nacimientomenor) {
        this.nacimientomenor = nacimientomenor;
    }
}

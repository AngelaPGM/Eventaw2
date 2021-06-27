package es.taw.eventaw.entity;

import es.taw.eventaw.dto.AnalisisDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Analisis {
    private Integer id;
    private String nombre;
    private Date fechamayor;
    private Date fechamenor;
    private Date fechaigual;
    private Double preciomayor;
    private Double preciomenor;
    private Double precioigual;
    private String ciudadevento;
    private String ciudadusuario;
    private Date edadmayor;
    private Date edadmenor;
    private Date edadigual;
    private String sexo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 30)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "FECHAMAYOR", nullable = true)
    public Date getFechamayor() {
        return fechamayor;
    }

    public void setFechamayor(Date fechamayor) {
        this.fechamayor = fechamayor;
    }

    @Basic
    @Column(name = "FECHAMENOR", nullable = true)
    public Date getFechamenor() {
        return fechamenor;
    }

    public void setFechamenor(Date fechamenor) {
        this.fechamenor = fechamenor;
    }

    @Basic
    @Column(name = "FECHAIGUAL", nullable = true)
    public Date getFechaigual() {
        return fechaigual;
    }

    public void setFechaigual(Date fechaigual) {
        this.fechaigual = fechaigual;
    }

    @Basic
    @Column(name = "PRECIOMAYOR", nullable = true, precision = 0)
    public Double getPreciomayor() {
        return preciomayor;
    }

    public void setPreciomayor(Double preciomayor) {
        this.preciomayor = preciomayor;
    }

    @Basic
    @Column(name = "PRECIOMENOR", nullable = true, precision = 0)
    public Double getPreciomenor() {
        return preciomenor;
    }

    public void setPreciomenor(Double preciomenor) {
        this.preciomenor = preciomenor;
    }

    @Basic
    @Column(name = "PRECIOIGUAL", nullable = true, precision = 0)
    public Double getPrecioigual() {
        return precioigual;
    }

    public void setPrecioigual(Double precioigual) {
        this.precioigual = precioigual;
    }

    @Basic
    @Column(name = "CIUDADEVENTO", nullable = true, length = 45)
    public String getCiudadevento() {
        return ciudadevento;
    }

    public void setCiudadevento(String ciudadevento) {
        this.ciudadevento = ciudadevento;
    }

    @Basic
    @Column(name = "CIUDADUSUARIO", nullable = true, length = 45)
    public String getCiudadusuario() {
        return ciudadusuario;
    }

    public void setCiudadusuario(String ciudadusuario) {
        this.ciudadusuario = ciudadusuario;
    }

    @Basic
    @Column(name = "EDADMAYOR", nullable = true)
    public Date getEdadmayor() {
        return edadmayor;
    }

    public void setEdadmayor(Date edadmayor) {
        this.edadmayor = edadmayor;
    }

    @Basic
    @Column(name = "EDADMENOR", nullable = true)
    public Date getEdadmenor() {
        return edadmenor;
    }

    public void setEdadmenor(Date edadmenor) {
        this.edadmenor = edadmenor;
    }

    @Basic
    @Column(name = "EDADIGUAL", nullable = true)
    public Date getEdadigual() {
        return edadigual;
    }

    public void setEdadigual(Date edadigual) {
        this.edadigual = edadigual;
    }

    @Basic
    @Column(name = "SEXO", nullable = true, length = 1)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analisis analisis = (Analisis) o;
        return Objects.equals(id, analisis.id) && Objects.equals(nombre, analisis.nombre) && Objects.equals(fechamayor, analisis.fechamayor) && Objects.equals(fechamenor, analisis.fechamenor) && Objects.equals(fechaigual, analisis.fechaigual) && Objects.equals(preciomayor, analisis.preciomayor) && Objects.equals(preciomenor, analisis.preciomenor) && Objects.equals(precioigual, analisis.precioigual) && Objects.equals(ciudadevento, analisis.ciudadevento) && Objects.equals(ciudadusuario, analisis.ciudadusuario) && Objects.equals(edadmayor, analisis.edadmayor) && Objects.equals(edadmenor, analisis.edadmenor) && Objects.equals(edadigual, analisis.edadigual) && Objects.equals(sexo, analisis.sexo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechamayor, fechamenor, fechaigual, preciomayor, preciomenor, precioigual, ciudadevento, ciudadusuario, edadmayor, edadmenor, edadigual, sexo);
    }

    @Transient
    public AnalisisDTO getDTO() {
        AnalisisDTO dto = new AnalisisDTO();
        dto.setId(this.id);
        dto.setNombre(this.nombre);
        dto.setFechamayor(this.fechamayor);
        dto.setFechamenor(this.fechamenor);
        dto.setPreciomayor(this.preciomayor);
        dto.setPreciomenor(this.preciomenor);
        dto.setNacimientomayor(this.edadmayor);
        dto.setNacimientomenor(this.edadmenor);
        dto.setSexo(this.sexo);
        return dto;
    }
}

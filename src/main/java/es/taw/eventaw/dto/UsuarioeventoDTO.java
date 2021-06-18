package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Entrada;
import es.taw.eventaw.entity.Usuario;

import java.sql.Date;
import java.util.Collection;

public class UsuarioeventoDTO {
    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String domicilio;
    private String ciudad;
    private Date fechanacimiento;
    private String sexo;
    private Collection<Entrada> entradasById;
    private Usuario usuarioByIdusuario;

    public UsuarioeventoDTO() {
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Collection<Entrada> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(Collection<Entrada> entradasById) {
        this.entradasById = entradasById;
    }

    public Usuario getUsuarioByIdusuario() {
        return usuarioByIdusuario;
    }

    public void setUsuarioByIdusuario(Usuario usuarioByIdusuario) {
        this.usuarioByIdusuario = usuarioByIdusuario;
    }
}
package es.taw.eventaw.entity;

import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.dto.UsuarioeventoDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuarioevento {
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
    @Column(name = "NOMBRE", nullable = false, length = 45)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "APELLIDO1", nullable = false, length = 45)
    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    @Basic
    @Column(name = "APELLIDO2", nullable = true, length = 45)
    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Basic
    @Column(name = "DOMICILIO", nullable = false, length = 45)
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Basic
    @Column(name = "CIUDAD", nullable = false, length = 45)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Basic
    @Column(name = "FECHANACIMIENTO", nullable = false)
    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    @Basic
    @Column(name = "SEXO", nullable = false, length = 1)
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
        Usuarioevento that = (Usuarioevento) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(domicilio, that.domicilio) && Objects.equals(ciudad, that.ciudad) && Objects.equals(fechanacimiento, that.fechanacimiento) && Objects.equals(sexo, that.sexo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido1, apellido2, domicilio, ciudad, fechanacimiento, sexo);
    }

    @OneToMany(mappedBy = "usuarioeventoByUsuario")
    public Collection<Entrada> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(Collection<Entrada> entradasById) {
        this.entradasById = entradasById;
    }

    @OneToOne
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByIdusuario() {
        return usuarioByIdusuario;
    }

    public void setUsuarioByIdusuario(Usuario usuarioByIdusuario) {
        this.usuarioByIdusuario = usuarioByIdusuario;
    }

    @Transient
    public UsuarioeventoDTO getDTO() {
        UsuarioeventoDTO dto = new UsuarioeventoDTO();
        dto.setId(this.id);
        dto.setNombre(this.nombre);
        dto.setApellido1(this.apellido1);
        dto.setApellido2(this.apellido2);
        dto.setDomicilio(this.domicilio);
        dto.setCiudad(this.ciudad);
        dto.setFechanacimiento(this.fechanacimiento);
        dto.setSexo(this.sexo);
        dto.setEntradasById(this.entradasById);
        dto.setUsuarioDTO(this.usuarioByIdusuario.getDTO());
        return dto;
    }
}


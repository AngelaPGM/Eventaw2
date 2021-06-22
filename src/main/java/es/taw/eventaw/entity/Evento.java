package es.taw.eventaw.entity;

import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Evento {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String ciudad;
    private Date fecha;
    private Date fechacompra;
    private Double precio;
    private Integer aforo;
    private Integer maxentradasusuario;
    private Integer numfilas;
    private Integer asientosfila;
    private Collection<Entrada> entradasById;
    private Usuario usuarioByCreador;
    private Collection<EventoEtiqueta> eventoEtiquetasById;

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
    @Column(name = "TITULO", nullable = false, length = 30)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "CIUDAD", nullable = true, length = 45)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Basic
    @Column(name = "FECHA", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "FECHACOMPRA", nullable = false)
    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    @Basic
    @Column(name = "PRECIO", nullable = false, precision = 0)
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "AFORO", nullable = false)
    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    @Basic
    @Column(name = "MAXENTRADASUSUARIO", nullable = false)
    public Integer getMaxentradasusuario() {
        return maxentradasusuario;
    }

    public void setMaxentradasusuario(Integer maxentradasusuario) {
        this.maxentradasusuario = maxentradasusuario;
    }

    @Basic
    @Column(name = "NUMFILAS", nullable = true)
    public Integer getNumfilas() {
        return numfilas;
    }

    public void setNumfilas(Integer numfilas) {
        this.numfilas = numfilas;
    }

    @Basic
    @Column(name = "ASIENTOSFILA", nullable = true)
    public Integer getAsientosfila() {
        return asientosfila;
    }

    public void setAsientosfila(Integer asientosfila) {
        this.asientosfila = asientosfila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id) && Objects.equals(titulo, evento.titulo) && Objects.equals(descripcion, evento.descripcion) && Objects.equals(ciudad, evento.ciudad) && Objects.equals(fecha, evento.fecha) && Objects.equals(fechacompra, evento.fechacompra) && Objects.equals(precio, evento.precio) && Objects.equals(aforo, evento.aforo) && Objects.equals(maxentradasusuario, evento.maxentradasusuario) && Objects.equals(numfilas, evento.numfilas) && Objects.equals(asientosfila, evento.asientosfila);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion, ciudad, fecha, fechacompra, precio, aforo, maxentradasusuario, numfilas, asientosfila);
    }

    @OneToMany(mappedBy = "eventoByEvento")
    public Collection<Entrada> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(Collection<Entrada> entradasById) {
        this.entradasById = entradasById;
    }

    @ManyToOne
    @JoinColumn(name = "CREADOR", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByCreador() {
        return usuarioByCreador;
    }

    public void setUsuarioByCreador(Usuario usuarioByCreador) {
        this.usuarioByCreador = usuarioByCreador;
    }

    @OneToMany(mappedBy = "eventoByEvento")
    public Collection<EventoEtiqueta> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(Collection<EventoEtiqueta> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }

    @Transient
    public EventoDTO getDTO() {
        EventoDTO dto = new EventoDTO();
        dto.setId(this.id);
        dto.setTitulo(this.titulo);
        dto.setDescripcion(this.descripcion);
        dto.setCiudad(this.ciudad);
        dto.setFecha(this.fecha);
        dto.setFechacompra(this.fechacompra);
        dto.setPrecio(this.precio);
        dto.setAforo(this.aforo);
        dto.setMaxentradasusuario(this.maxentradasusuario);
        dto.setNumfilas(this.numfilas);
        dto.setAsientosfila(this.asientosfila);
        dto.setEntradasDTOById(this.getDTO().getEntradasDTOById());
        dto.setUsuarioDTOByCreador(this.getDTO().getUsuarioDTOByCreador());
        dto.setEventoEtiquetasDTOById(this.getDTO().getEventoEtiquetasDTOById());

        return dto;
    }
}

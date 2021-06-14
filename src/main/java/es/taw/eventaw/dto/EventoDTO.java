package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Entrada;
import es.taw.eventaw.entity.EventoEtiqueta;
import es.taw.eventaw.entity.Usuario;

import java.sql.Date;
import java.util.Collection;

public class EventoDTO {
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

    public EventoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Integer getMaxentradasusuario() {
        return maxentradasusuario;
    }

    public void setMaxentradasusuario(Integer maxentradasusuario) {
        this.maxentradasusuario = maxentradasusuario;
    }

    public Integer getNumfilas() {
        return numfilas;
    }

    public void setNumfilas(Integer numfilas) {
        this.numfilas = numfilas;
    }

    public Integer getAsientosfila() {
        return asientosfila;
    }

    public void setAsientosfila(Integer asientosfila) {
        this.asientosfila = asientosfila;
    }

    public Collection<Entrada> getEntradasById() {
        return entradasById;
    }

    public void setEntradasById(Collection<Entrada> entradasById) {
        this.entradasById = entradasById;
    }

    public Usuario getUsuarioByCreador() {
        return usuarioByCreador;
    }

    public void setUsuarioByCreador(Usuario usuarioByCreador) {
        this.usuarioByCreador = usuarioByCreador;
    }

    public Collection<EventoEtiqueta> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(Collection<EventoEtiqueta> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }
}

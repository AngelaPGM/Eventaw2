package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.Usuarioevento;

import java.sql.Date;

public class EntradaDTO {
    private Integer id;
    private Integer numfila;
    private Integer asientofila;
    //private Usuarioevento usuarioeventoByUsuario;
    //private Evento eventoByEvento;
    //Evento
    private Date fechaEvento;
    private Double precioEvento;
    //Usuario
    private Date fechaUsuario;
    private String sexo;

    public EntradaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumfila() {
        return numfila;
    }

    public void setNumfila(Integer numfila) {
        this.numfila = numfila;
    }

    public Integer getAsientofila() {
        return asientofila;
    }

    public void setAsientofila(Integer asientofila) {
        this.asientofila = asientofila;
    }

    /*
    public Usuarioevento getUsuarioeventoByUsuario() {
        return usuarioeventoByUsuario;
    }

    public void setUsuarioeventoByUsuario(Usuarioevento usuarioeventoByUsuario) {
        this.usuarioeventoByUsuario = usuarioeventoByUsuario;
    }

    public Evento getEventoByEvento() {
        return eventoByEvento;
    }

    public void setEventoByEvento(Evento eventoByEvento) {
        this.eventoByEvento = eventoByEvento;
    }
    */

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public Double getPrecioEvento() {
        return precioEvento;
    }

    public Date getFechaUsuario() {
        return fechaUsuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public void setPrecioEvento(Double precioEvento) {
        this.precioEvento = precioEvento;
    }

    public void setFechaUsuario(Date fechaUsuario) {
        this.fechaUsuario = fechaUsuario;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}

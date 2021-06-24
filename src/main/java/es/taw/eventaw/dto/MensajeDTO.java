package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Mensaje;

import java.util.Date;

public class MensajeDTO {
    private Integer id;
    private String contenido;
    private Date fecha;
    private UsuarioDTO emisor;
    private ConversacionDTO conversacion;

    public MensajeDTO() {
        this.fecha = new java.sql.Date(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioDTO getEmisor() {
        return emisor;
    }

    public void setEmisor(UsuarioDTO emisor) {
        this.emisor = emisor;
    }

    public ConversacionDTO getConversacion() {
        return conversacion;
    }

    public void setConversacion(ConversacionDTO conversacion) {
        this.conversacion = conversacion;
    }
}

package es.taw.eventaw.dto;


import java.sql.Date;

public class MensajeDTO {
    private Integer id;
    private String contenido;
    private Date fecha;
    private UsuarioDTO usuarioDTOByEmisor;
    private ConversacionDTO conversacionDTOByConversacion;

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

    public UsuarioDTO getUsuarioDTOByEmisor() {
        return usuarioDTOByEmisor;
    }

    public void setUsuarioDTOByEmisor(UsuarioDTO usuarioDTOByEmisor) {
        this.usuarioDTOByEmisor = usuarioDTOByEmisor;
    }

    public ConversacionDTO getConversacionDTOByConversacion() {
        return conversacionDTOByConversacion;
    }

    public void setConversacionDTOByConversacion(ConversacionDTO conversacionDTOByConversacion) {
        this.conversacionDTOByConversacion = conversacionDTOByConversacion;
    }
}

package es.taw.eventaw.dto;

import es.taw.eventaw.entity.*;

import java.util.Collection;

public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String contrasenya;
    private String contrasenya2;
    private Collection<Conversacion> conversacionsById;
    private Collection<Conversacion> conversacionsById_0;
    private Collection<EventoDTO> eventosById;
    private Collection<Mensaje> mensajesById;
    private Rol rolByRol;
    private UsuarioeventoDTO usuarioeventoDTO;

    public UsuarioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getContrasenya2() { return contrasenya2; }

    public void setContrasenya2(String contrasenya2) { this.contrasenya2 = contrasenya2; }

    public Collection<Conversacion> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<Conversacion> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Collection<Conversacion> getConversacionsById_0() {
        return conversacionsById_0;
    }

    public void setConversacionsById_0(Collection<Conversacion> conversacionsById_0) {
        this.conversacionsById_0 = conversacionsById_0;
    }

    public Collection<EventoDTO> getEventosById() {
        return eventosById;
    }

    public void setEventosById(Collection<EventoDTO> eventosById) {
        this.eventosById = eventosById;
    }

    public Collection<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Rol getRolByRol() {
        return rolByRol;
    }

    public void setRolByRol(Rol rolByRol) {
        this.rolByRol = rolByRol;
    }

    public UsuarioeventoDTO getUsuarioeventoDTO() {
        return usuarioeventoDTO;
    }

    public void setUsuarioeventoDTO(UsuarioeventoDTO usuarioeventoDTO) {
        this.usuarioeventoDTO = usuarioeventoDTO;
    }
}

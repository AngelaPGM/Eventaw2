package es.taw.eventaw.dto;

import java.util.Collection;

public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String contrasenya;
    private String contrasenya2;
    private Collection<ConversacionDTO> conversacionsDTOById;
    private Collection<ConversacionDTO> conversacionsDTOById_0;
    private Collection<EventoDTO> eventosDTOById;
    private Collection<MensajeDTO> mensajesDTOById;
    private RolDTO rolDTOByRol;
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

    public Collection<ConversacionDTO> getConversacionsDTOById() {
        return conversacionsDTOById;
    }

    public void setConversacionsDTOById(Collection<ConversacionDTO> conversacionsDTOById) {
        this.conversacionsDTOById = conversacionsDTOById;
    }

    public Collection<ConversacionDTO> getConversacionsDTOById_0() {
        return conversacionsDTOById_0;
    }

    public void setConversacionsDTOById_0(Collection<ConversacionDTO> conversacionsDTOById_0) {
        this.conversacionsDTOById_0 = conversacionsDTOById_0;
    }

    public Collection<EventoDTO> getEventosDTOById() {
        return eventosDTOById;
    }

    public void setEventosDTOById(Collection<EventoDTO> eventosDTOById) {
        this.eventosDTOById = eventosDTOById;
    }

    public Collection<MensajeDTO> getMensajesDTOById() {
        return mensajesDTOById;
    }

    public void setMensajesDTOById(Collection<MensajeDTO> mensajesDTOById) {
        this.mensajesDTOById = mensajesDTOById;
    }

    public RolDTO getRolDTOByRol() {
        return rolDTOByRol;
    }

    public void setRolDTOByRol(RolDTO rolDTOByRol) {
        this.rolDTOByRol = rolDTOByRol;
    }

    public UsuarioeventoDTO getUsuarioeventoDTO() {
        return usuarioeventoDTO;
    }

    public void setUsuarioeventoDTO(UsuarioeventoDTO usuarioeventoDTO) {
        this.usuarioeventoDTO = usuarioeventoDTO;
    }
}

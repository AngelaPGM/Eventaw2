package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Conversacion;
import es.taw.eventaw.entity.Mensaje;
import es.taw.eventaw.entity.Rol;
import es.taw.eventaw.entity.Usuarioevento;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String contrasenya;
    private String contrasenya2;
    private RolDTO rolDTOByRol;
    private UsuarioeventoDTO usuarioeventoDTOById;
    private List<EventoDTO> eventosDTO;
    private List<ConversacionDTO> conversacionesDTO;
    private List<MensajeDTO> mensajesDTO;

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

    public String getContrasenya2() {
        return contrasenya2;
    }

    public void setContrasenya2(String contrasenya2) {
        this.contrasenya2 = contrasenya2;
    }

    public RolDTO getRolDTOByRol() {
        return rolDTOByRol;
    }

    public void setRolDTOByRol(RolDTO rolDTOByRol) {
        this.rolDTOByRol = rolDTOByRol;
    }

    public UsuarioeventoDTO getUsuarioeventoDTOById() {
        return usuarioeventoDTOById;
    }

    public void setUsuarioeventoDTOById(UsuarioeventoDTO usuarioeventoDTOById) {
        this.usuarioeventoDTOById = usuarioeventoDTOById;
    }

    public List<EventoDTO> getEventosDTO() {
        if(eventosDTO == null) return new ArrayList<>();
        return eventosDTO;
    }

    public void setEventosDTO(List<EventoDTO> eventosDTO) {
        this.eventosDTO = eventosDTO;
    }

    public List<ConversacionDTO> getConversacionesDTO() {
        if(conversacionesDTO == null) return new ArrayList<>();
        return conversacionesDTO;
    }

    public void setConversacionesDTO(List<ConversacionDTO> conversacionesDTO) {
        this.conversacionesDTO = conversacionesDTO;
    }

    public List<MensajeDTO> getMensajesDTO() {
        if(mensajesDTO == null) return new ArrayList<>();
        return mensajesDTO;
    }

    public void setMensajesDTO(List<Mensaje> mensajes) {
        List<MensajeDTO> lista = new ArrayList<>();
        for(Mensaje m : mensajes){
            lista.add(m.getDTO());
        }
        this.mensajesDTO = mensajesDTO;
    }
}

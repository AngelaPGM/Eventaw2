package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Conversacion;
import es.taw.eventaw.entity.Entrada;
import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.Mensaje;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public String getContrasenya2() {
        return contrasenya2;
    }

    public void setContrasenya2(String contrasenya2) {
        this.contrasenya2 = contrasenya2;
    }

    public Collection<ConversacionDTO> getConversacionsDTOById() {
        return conversacionsDTOById;
    }

    public void setConversacionsDTOById(Collection<Conversacion> conversacionsById) {
        List<ConversacionDTO> listaDto = new ArrayList<>();
        if (conversacionsById != null) {
            for (Conversacion c : conversacionsById) {
                listaDto.add(c.getDTO());
            }
        }
        this.conversacionsDTOById = listaDto;
    }

    public Collection<ConversacionDTO> getConversacionsDTOById_0() {
        return conversacionsDTOById_0;
    }

    public void setConversacionsDTOById_0(Collection<Conversacion> conversacionsById_0) {
        List<ConversacionDTO> listaDto = new ArrayList<>();
        if (conversacionsById_0 != null) {
            for (Conversacion c : conversacionsById_0) {
                listaDto.add(c.getDTO());
            }
        }
        this.conversacionsDTOById_0 = listaDto;
    }

    public Collection<EventoDTO> getEventosDTOById() {
        return eventosDTOById;
    }

    public void setEventosDTOById(Collection<Evento> eventosById) {
        List<EventoDTO> listaDto = new ArrayList<>();
        if (eventosById != null) {
            for (Evento e : eventosById) {
                listaDto.add(e.getDTO());
            }
        }
        this.eventosDTOById = listaDto;
    }

    public Collection<MensajeDTO> getMensajesDTOById() {
        return mensajesDTOById;
    }

    public void setMensajesDTOById(Collection<Mensaje> mensajesById) {
        List<MensajeDTO> listaDto = new ArrayList<>();
        if (mensajesById != null) {
            for (Mensaje m : mensajesById) {
                listaDto.add(m.getDTO());
            }
        }
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

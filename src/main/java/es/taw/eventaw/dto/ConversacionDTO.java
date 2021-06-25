package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class ConversacionDTO {
    private Integer id;
    private UsuarioDTO usuarioConversacion;
    private UsuarioDTO teleoperadorConversacion;
    private List<MensajeDTO> mensajesById;

    public ConversacionDTO(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDTO getUsuarioConversacion() {
        return usuarioConversacion;
    }

    public void setUsuarioConversacion(UsuarioDTO usuarioConversacion) {
        this.usuarioConversacion = usuarioConversacion;
    }

    public UsuarioDTO getTeleoperadorConversacion() {
        return teleoperadorConversacion;
    }

    public void setTeleoperadorConversacion(UsuarioDTO teleoperadorConversacion) {
        this.teleoperadorConversacion = teleoperadorConversacion;
    }

    public List<MensajeDTO> getMensajesById() {
        if(mensajesById == null) return new ArrayList<>();
        return mensajesById;
    }

    public void setMensajesById(List<Mensaje> lista) {
        List<MensajeDTO> listaDto = new ArrayList<>();
        for(Mensaje m : lista){
            listaDto.add(m.getDTO());
        }
        this.mensajesById = listaDto;
    }
}

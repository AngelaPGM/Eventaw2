package es.taw.eventaw.dto;

import java.util.Collection;

public class ConversacionDTO {
    private Integer id;
    private UsuarioDTO  usuarioDTOByTeleoperador;
    private UsuarioDTO usuarioDTOByUsuario;
    private Collection<MensajeDTO> mensajesDTOById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDTO getUsuarioDTOByTeleoperador() {
        return usuarioDTOByTeleoperador;
    }

    public void setUsuarioDTOByTeleoperador(UsuarioDTO usuarioDTOByTeleoperador) {
        this.usuarioDTOByTeleoperador = usuarioDTOByTeleoperador;
    }

    public UsuarioDTO getUsuarioDTOByUsuario() {
        return usuarioDTOByUsuario;
    }

    public void setUsuarioDTOByUsuario(UsuarioDTO usuarioDTOByUsuario) {
        this.usuarioDTOByUsuario = usuarioDTOByUsuario;
    }

    public Collection<MensajeDTO> getMensajesDTOById() {
        return mensajesDTOById;
    }

    public void setMensajesDTOById(Collection<MensajeDTO> mensajesDTOById) {
        this.mensajesDTOById = mensajesDTOById;
    }
}

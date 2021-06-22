package es.taw.eventaw.dto;

import java.util.Collection;

public class RolDTO {
    private Integer id;
    private String tipo;
    private Collection<UsuarioDTO> usuariosById;

    public RolDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Collection<UsuarioDTO> getUsuariosById() {
        return usuariosById;
    }

    public void setUsuariosById(Collection<UsuarioDTO> usuariosById) {
        this.usuariosById = usuariosById;
    }
}

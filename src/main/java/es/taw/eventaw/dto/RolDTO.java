package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Usuario;

import java.util.Collection;

public class RolDTO {
    private Integer id;
    private String tipo;
    private Collection<Usuario> usuariosById;

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

    public Collection<Usuario> getUsuariosById() {
        return usuariosById;
    }

    public void setUsuariosById(Collection<Usuario> usuariosById) {
        this.usuariosById = usuariosById;
    }
}

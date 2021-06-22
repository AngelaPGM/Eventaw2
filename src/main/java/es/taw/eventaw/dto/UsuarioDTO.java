package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Rol;
import es.taw.eventaw.entity.Usuarioevento;

public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String contrasenya;
    private String contrasenya2;
    private RolDTO rolDTOByRol;
    private UsuarioeventoDTO usuarioeventoDTOById;

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
}

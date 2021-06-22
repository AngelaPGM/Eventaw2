package es.taw.eventaw.service;

import es.taw.eventaw.dao.RolRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.dto.UsuarioeventoDTO;
import es.taw.eventaw.entity.Rol;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private UsuarioeventoService usuarioeventoService;

    @Autowired
    public void setUsuarioeventoService(UsuarioeventoService usuarioeventoService) {
        this.usuarioeventoService = usuarioeventoService;
    }

    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO comprobarCredenciales(String correo, String pass) {
        UsuarioDTO userDTO = null;
        Usuario user = this.usuarioRepository.findUsuarioByCorreoAndContrasenya(correo, pass);
        if (user != null) userDTO = user.getDTO();
        return userDTO;
    }

    public UsuarioDTO nuevoUsuario(UsuarioeventoDTO inputDataDTO){
        Usuario user = new Usuario();
        Rol rol = this.rolRepository.getById(2);//Si borro esto me da una violacion de campo NotNull
        user.setRolByRol(rol);
        //user.setCorreo(inputDataDTO.getUsuarioByIdusuario().getCorreo());
        //user.setContrasenya(inputDataDTO.getUsuarioByIdusuario().getContrasenya());
        this.usuarioRepository.save(user);
        this.usuarioeventoService.nuevoUsuarioevento(user, inputDataDTO);

        return user.getDTO();
    }
}

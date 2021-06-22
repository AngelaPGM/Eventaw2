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

    public UsuarioDTO nuevoUsuario(UsuarioeventoDTO inputDataDTO) {
        Usuario user = new Usuario();
        Rol rol = this.rolRepository.getById(2);//Si borro esto me da una violacion de campo NotNull
        user.setRolByRol(rol);
        user.setCorreo(inputDataDTO.getUsuarioDTO().getCorreo());
        user.setContrasenya(inputDataDTO.getUsuarioDTO().getContrasenya());
        this.usuarioRepository.save(user);
        this.usuarioeventoService.nuevoUsuarioevento(user, inputDataDTO);

        return user.getDTO();
    }

    public void guardarUsuario(UsuarioDTO dto) {
        Usuario usuario;
        Rol r;

        if (dto.getId() == null) {
            usuario = new Usuario();
            r = this.rolRepository.findById(2).orElse(null);
            dto.setId(0);
            dto.setRolDTOByRol(r.getDTO());
            dto.getUsuarioeventoDTOById().setId(0);
            dto.getUsuarioeventoDTOById().setUsuarioDTO(dto);

        } else {
            usuario = this.usuarioRepository.findById(dto.getId()).orElse(new Usuario());
             r = this.rolRepository.findById(dto.getRolDTOByRol().getId()).orElse(null);

        }

        usuario.setId(dto.getId());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasenya(dto.getContrasenya());
        usuario.setRolByRol(r);


        this.usuarioeventoService.guardarUsuarioevento(dto);
        this.usuarioRepository.save(usuario);

    }
}

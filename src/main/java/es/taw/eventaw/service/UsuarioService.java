package es.taw.eventaw.service;

import es.taw.eventaw.dao.RolRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.dto.UsuarioeventoDTO;
import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.Rol;
import es.taw.eventaw.entity.Usuario;
import es.taw.eventaw.entity.Usuarioevento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void guardarUsuario(UsuarioDTO dto) {
        Usuario usuario;
        Rol r;

        if (dto.getId() == null) {
            usuario = new Usuario();
            r = this.rolRepository.findById(2).orElse(new Rol());

        } else {
            usuario = this.usuarioRepository.findById(dto.getId()).orElse(new Usuario());
             r = this.rolRepository.findById(dto.getRolDTOByRol().getId()).orElse(new Rol());
        }

        usuario.setId(dto.getId());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasenya(dto.getContrasenya());
        usuario.setRolByRol(r);

        this.usuarioRepository.save(usuario);
        this.usuarioeventoService.guardarUsuarioevento(usuario, dto.getUsuarioeventoDTOById());
    }

    public List<EventoDTO> getEventos(UsuarioDTO userDTO) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(userDTO.getId());
        List<EventoDTO> eventosDTO = new ArrayList<>();
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            List<Evento> aux = (List<Evento>) usuario.getEventosById();
            eventosDTO = this.listaToDto(aux);
        }
        return eventosDTO;
    }

    private List<EventoDTO> listaToDto(List<Evento> lista){
        if(lista == null){
            return new ArrayList<>();
        }else{
            List<EventoDTO> listaDto = new ArrayList<>();
            for(Evento e: lista){
                listaDto.add(e.getDTO());
            }
            return listaDto;
        }
    }

    public void updateUsuario(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    public Usuario findByUsuario(UsuarioDTO creador) {
        Optional<Usuario> usuarioOtp = this.usuarioRepository.findById(creador.getId());
        if(usuarioOtp.isPresent()){
            Usuario usuario = usuarioOtp.get();
            return usuario;
        }
        return null;
    }
}

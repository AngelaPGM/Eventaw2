package es.taw.eventaw.service;

import es.taw.eventaw.dao.RolRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.RolDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.dto.UsuarioeventoDTO;
import es.taw.eventaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

    public UsuarioDTO comprobarCredenciales(String correo, String pass) throws ParseException {
        UsuarioDTO userDTO = null;
        Usuario user = this.usuarioRepository.findUsuarioByCorreoAndContrasenya(correo, pass);
        if (user != null) userDTO = user.getDTO();
        return userDTO;
    }

    public void guardarUsuario(UsuarioDTO dto, Integer rol) {
        Usuario usuario;
        Rol r;

        if (dto.getId() == null) {
            usuario = new Usuario();
            r = this.rolRepository.findById(rol).orElse(new Rol());

        } else {
            usuario = this.usuarioRepository.findById(dto.getId()).orElse(new Usuario());
             r = this.rolRepository.findById(dto.getRolDTOByRol().getId()).orElse(new Rol());
        }

        usuario.setId(dto.getId());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasenya(dto.getContrasenya());
        usuario.setRolByRol(r);

        this.usuarioRepository.save(usuario);
        if(usuario.getRolByRol().getId() == 2) {
            this.usuarioeventoService.guardarUsuarioevento(usuario, dto.getUsuarioeventoDTOById());
        }
    }


    public List<EventoDTO> getEventos(UsuarioDTO userDTO) throws ParseException {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(userDTO.getId());
        List<EventoDTO> eventosDTO = new ArrayList<>();
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            List<Evento> aux = (List<Evento>) usuario.getEventosById();
            eventosDTO = this.listaEventosToDto(aux);
        }
        return eventosDTO;
    }

    private List<EventoDTO> listaEventosToDto(List<Evento> lista) throws ParseException {
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
    private List<UsuarioDTO> listaUsuariosToDto(List<Usuario> lista) throws ParseException {
        if(lista == null){
            return new ArrayList<>();
        }else{
            List<UsuarioDTO> listaDto = new ArrayList<>();
            for(Usuario u: lista){
                listaDto.add(u.getDTO());
            }
            return listaDto;
        }
    }
    public List<UsuarioDTO> findAll() throws ParseException {
        List<Usuario> listaUsuario = this.usuarioRepository.findAll();

        return this.listaUsuariosToDto(listaUsuario);
    }
    public List<UsuarioDTO> filtradoUsuario(String filtro,String tipoFiltro) throws ParseException {
        List<Usuario> filtrados = new ArrayList<>();
        if(!filtro.equals("")){
            if(tipoFiltro.equals("ID")){

                Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(new Integer(filtro));
                if(optionalUsuario.isPresent()){
                    Usuario u = optionalUsuario.get();
                    filtrados.add(u);
                }
            }else if (tipoFiltro.equals("CORREO")){
                filtrados = this.usuarioRepository.findBySimilarCorreo(filtro);
            }else{
                filtrados = this.usuarioRepository.findByRol(new Integer(filtro));
            }
        }
        return this.listaUsuariosToDto(filtrados);

    }

    public UsuarioDTO findUsuarioEventobyId(Integer id) throws ParseException {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            return usuario.getDTO();
        }
        return null;
    }
    private List<RolDTO> listaRolToDto(List<Rol> lista){
        if(lista == null){
            return new ArrayList<>();
        }else{
            List<RolDTO> listaDto = new ArrayList<>();
            for(Rol r : lista){
                listaDto.add(r.getDTO());
            }
            return listaDto;
        }
    }


    public List<RolDTO> findAllRol(){
        List<Rol> listaRol = this.rolRepository.findAll();

        return this.listaRolToDto(listaRol);

    }

    public Integer getIdRolUsuario(UsuarioDTO dto) {
        Usuario usuario = this.usuarioRepository.findUsuarioById(dto.getId());
        return usuario.getRolByRol().getId();
    }

}

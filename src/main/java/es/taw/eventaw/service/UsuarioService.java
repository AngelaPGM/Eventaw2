package es.taw.eventaw.service;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.sun.istack.NotNull;
import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.RolRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dao.UsuarioeventoRepository;
import es.taw.eventaw.dto.*;
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
    private UsuarioeventoRepository usuarioeventoRepository;
    private RolRepository rolRepository;
    private UsuarioeventoService usuarioeventoService;
    private EventoService eventoService;
    private ConversacionService conversacionService;
    private EntradaService entradaService;


    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Autowired
    public void setConversacionService(ConversacionService conversacionService) {
        this.conversacionService = conversacionService;
    }

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Autowired
    public void setUsuarioeventoRepository(UsuarioeventoRepository usuarioeventoRepository) {
        this.usuarioeventoRepository = usuarioeventoRepository;
    }

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

    public UsuarioDTO guardarUsuario(UsuarioDTO dto, Integer rol) {
        Usuario usuario;
        Rol r;

        if (dto.getId() == null) {
            usuario = new Usuario();

        } else {
            usuario = this.usuarioRepository.findById(dto.getId()).orElse(new Usuario());
        }
        r = this.rolRepository.findById(rol).orElse(new Rol());
        dto.setRolDTOByRol(r.getDTO());

        usuario.setId(dto.getId());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasenya(dto.getContrasenya());
        usuario.setRolByRol(r);
        usuario.setMensajesById(new ArrayList<>());
        this.usuarioRepository.save(usuario);

        if(usuario.getRolByRol().getId() == 2) {
            Usuarioevento uevento = this.usuarioeventoService.guardarUsuarioevento(usuario, dto.getUsuarioeventoDTOById());
            usuario.setUsuarioeventosById(uevento);
            this.usuarioRepository.save(usuario);
        }

        return dto;
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

    public void remove(Integer id) throws ParseException {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            if(usuario.getRolByRol().getId() == 1){
                this.usuarioRepository.delete(usuario);
            }else if(usuario.getDTO().getRolDTOByRol().getId() == 2){ //UsuarioEvento
                if(usuario.getUsuarioeventosById().getEntradasById() != null){
                        this.entradaService.removeAllFromList((List<Entrada>) usuario.getUsuarioeventosById().getEntradasById());
                }

                if(usuario.getConversacionsById_0() != null){
                    for(Conversacion c : usuario.getConversacionsById_0()){
                        this.conversacionService.borrar(c.getId());
                    }
                }

                this.usuarioeventoRepository.delete(usuario.getUsuarioeventosById());
                this.usuarioRepository.delete(usuario);
            }else if(usuario.getDTO().getRolDTOByRol().getId() == 3){//Creador Eventos
                if(usuario.getEventosById() != null){
                    for(Evento e : usuario.getEventosById()){
                        this.eventoService.remove(e.getId());

                    }
                }
                if(usuario.getConversacionsById_0() != null){
                    for(Conversacion c : usuario.getConversacionsById_0()){
                        this.conversacionService.borrar(c.getId());
                    }
                }
                this.usuarioRepository.delete(usuario);
            }else if(usuario.getDTO().getRolDTOByRol().getId() == 4){ //Teleoperador
                if(usuario.getConversacionsById_0() != null){
                    for(Conversacion c : usuario.getConversacionsById_0()){
                        this.conversacionService.borrar(c.getId());
                    }
                }
                this.usuarioRepository.delete(usuario);
            }else{ //Analista
                this.usuarioRepository.delete(usuario);
            }
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

    public Boolean getCorreoYaRegistrado(String correo) {
        return (this.usuarioRepository.findUsuarioByCorreo(correo) != null);
    }

    public List<UsuarioDTO> getByRol(Integer idRol) throws ParseException {
        List<UsuarioDTO> res = new ArrayList<>();
        List<Usuario> usuarios = this.usuarioRepository.findByRol(idRol);
        for(Usuario u : usuarios){
            res.add(u.getDTO());
        }
        return res;
    }
}

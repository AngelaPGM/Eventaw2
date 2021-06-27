package es.taw.eventaw.service;

import es.taw.eventaw.dao.ConversacionRepository;
import es.taw.eventaw.dao.MensajeRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dto.ConversacionDTO;
import es.taw.eventaw.dto.MensajeDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Conversacion;
import es.taw.eventaw.entity.Mensaje;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ConversacionService {


    ConversacionRepository conversacionRepository;
    UsuarioRepository usuarioRepository;
    MensajeRepository mensajeRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setConversacionRepository(ConversacionRepository conversacionRepository) {
        this.conversacionRepository = conversacionRepository;
    }

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    public List<ConversacionDTO> getChatsDto() throws ParseException {
        List<ConversacionDTO> listaDto = new ArrayList<>();
        List<Conversacion> lista = new ArrayList<>();
        lista = this.conversacionRepository.findAll();
        for(Conversacion c : lista){
            listaDto.add(c.getDTO());
        }

        return listaDto;
    }

    public List<ConversacionDTO> getChatsByCorreo(String correo) throws ParseException {
        List<ConversacionDTO> listaDto = new ArrayList<>();
        List<Conversacion> lista = new ArrayList<>();
        lista = this.conversacionRepository.findByCorreo(correo);
        for(Conversacion c : lista){
            listaDto.add(c.getDTO());
        }

        return listaDto;
    }

    public ConversacionDTO crearConversacion(Integer id) throws ParseException {
        List<Usuario> tvops = this.usuarioRepository.findByRol(4);
        Integer total = tvops.size();
        Random r = new Random();
        Integer chosen = r.nextInt(total);
        Usuario to = tvops.get(chosen);

        Conversacion c = new Conversacion();
        Usuario u = this.usuarioRepository.findUsuarioById(id);
        c.setUsuarioByUsuario(u);
        c.setUsuarioByTeleoperador(to);
        List<Mensaje> mensajes = new ArrayList<>();
        c.setMensajesById(mensajes);
        this.conversacionRepository.save(c);

        return c.getDTO();
    }

    public List<MensajeDTO> getMensajesConversacionById(Integer id) throws ParseException {
        List<MensajeDTO> listaMensajesDto = new ArrayList<>();
        Conversacion c = this.conversacionRepository.findById(new Integer(id)).orElse(new Conversacion());
        List<Mensaje> MensajesConversacion = (List<Mensaje>) c.getMensajesById();
        for(Mensaje m : MensajesConversacion){
            listaMensajesDto.add(m.getDTO());
        }
        return listaMensajesDto;
    }

    public ConversacionDTO getConversacionById(Integer id) throws ParseException {
        Conversacion c = this.conversacionRepository.findById(new Integer(id)).orElse(new Conversacion());
        ConversacionDTO conversacionDTO = c.getDTO();

        return conversacionDTO;
    }

    public void borrar(Integer id) throws ParseException {
        Optional<Conversacion> c = this.conversacionRepository.findById(id);
        if (c.isPresent()) {
            Conversacion conversacion = c.get();
            for(Mensaje m : conversacion.getMensajesById()){
                this.mensajeRepository.delete(m);
            }
            this.conversacionRepository.delete(conversacion);
        }
    }
}

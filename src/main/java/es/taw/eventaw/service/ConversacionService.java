package es.taw.eventaw.service;

import es.taw.eventaw.dao.ConversacionRepository;
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
@Service
public class ConversacionService {


    ConversacionRepository conversacionRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setConversacionRepository(ConversacionRepository conversacionRepository) {
        this.conversacionRepository = conversacionRepository;
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
    public List<MensajeDTO> getMensajesConversacionById(Integer id){
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
}

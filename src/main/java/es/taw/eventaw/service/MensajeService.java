package es.taw.eventaw.service;

import es.taw.eventaw.dao.ConversacionRepository;
import es.taw.eventaw.dao.MensajeRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dao.UsuarioeventoRepository;
import es.taw.eventaw.dto.MensajeDTO;
import es.taw.eventaw.entity.Conversacion;
import es.taw.eventaw.entity.Mensaje;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;


@Service
public class MensajeService {

    ConversacionRepository conversacionRepository;
    UsuarioRepository usuarioRepository;
    UsuarioeventoRepository usuarioeventoRepository;
    MensajeRepository mensajeRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setUsuarioeventoRepository(UsuarioeventoRepository usuarioeventoRepository) {
        this.usuarioeventoRepository = usuarioeventoRepository;
    }

    @Autowired
    public void setConversacionRepository(ConversacionRepository conversacionRepository) {
        this.conversacionRepository = conversacionRepository;
    }

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    public MensajeDTO guardar(MensajeDTO mensajeDTO) throws ParseException {
        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(mensajeDTO.getContenido());
        mensaje.setFecha(mensajeDTO.getFecha());
        Usuario u = this.usuarioRepository.findUsuarioByCorreo(mensajeDTO.getEmisor().getCorreo());
        mensaje.setUsuarioByEmisor(u);
        Optional<Conversacion> c = this.conversacionRepository.findById(mensajeDTO.getConversacion().getId());
        if (c.isPresent()) {
            Conversacion conversacion = c.get();
            mensaje.setConversacionByConversacion(conversacion);
            Collection<Mensaje> col = c.get().getMensajesById();
            col.add(mensaje);
            conversacion.setMensajesById(col);
            this.mensajeRepository.save(mensaje);
            this.conversacionRepository.save(conversacion);
        }
        return mensaje.getDTO();
    }
}

package es.taw.eventaw.service;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> findAll() {
        return this.eventoRepository.findAll();
    }
}

package es.taw.eventaw.service;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public List<EventoDTO>  findEventosFuturos() {
        List<Evento> eventosFuturos = this.eventoRepository.findEventoByFechaAfter(new Date());
        return this.toListaDTO(eventosFuturos);
    }

    protected List<EventoDTO> toListaDTO(List<Evento> lista) {
        List<EventoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<EventoDTO>();
            for (Evento e : lista) {
                listaDTO.add(e.getDTO());
            }
        }
        return listaDTO;
    }
}

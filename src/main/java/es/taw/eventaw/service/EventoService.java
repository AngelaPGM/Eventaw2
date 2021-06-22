package es.taw.eventaw.service;

import es.taw.eventaw.dao.EventoRepository;
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

    public List<EventoDTO> filtradoNombre(String titulo, Date fechaIni, Date fechaFin) {
        List<Evento> filtrados = new ArrayList<>();

        if(titulo != null && fechaIni != null && fechaFin != null){//Filtrado completo
            filtrados = this.eventoRepository.findEventoByTituloAndFechaAfterAndFechaBefore(titulo, fechaIni, fechaFin);
        } else if(titulo != null && fechaIni != null && fechaFin == null){// Nombre y Fecha Inicio
            filtrados = this.eventoRepository.findEventoByTituloAndFechaAfter(titulo, fechaIni);
        } else if(titulo != null && fechaIni == null && fechaFin != null){// Nombre y Fecha Final
            filtrados = this.eventoRepository.findEventoByTituloAndFechaBefore(titulo, fechaFin);
        } else if(titulo != null && fechaIni == null && fechaFin == null){//Solo Nombre
            filtrados = this.eventoRepository.findEventoByTitulo(titulo);
        } else if(titulo == null && fechaIni != null && fechaFin != null){// Solo Fechas
            filtrados = this.eventoRepository.findEventoByFechaAfterAndFechaBefore(fechaIni, fechaFin);
        } else if(titulo == null && fechaIni != null && fechaFin == null){// Solo Fecha Inicio
            filtrados = this.eventoRepository.findEventoByFechaAfter(fechaIni);
        } else if(titulo == null && fechaIni == null && fechaFin != null){// Solo Fecha Final
            filtrados = this.eventoRepository.findEventoByFechaBefore(fechaFin);
        }

        return this.toListaDTO(filtrados);
    }

}

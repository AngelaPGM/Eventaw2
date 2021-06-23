package es.taw.eventaw.service;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Entrada;
import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {
    private EventoRepository eventoRepository;
    private UsuarioService usuarioService;
    private EntradaService entradaService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> findAll() {
        return this.eventoRepository.findAll();
    }

    public List<EventoDTO>  findEventosFuturos() throws ParseException {
        List<Evento> eventosFuturos = this.eventoRepository.findEventoByFechaAfter(new Date());
        return this.toListaDTO(eventosFuturos);
    }

    protected List<EventoDTO> toListaDTO(List<Evento> lista) throws ParseException {
        List<EventoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<EventoDTO>();
            for (Evento e : lista) {
                listaDTO.add(e.getDTO());
            }
        }
        return listaDTO;
    }

    public List<EventoDTO> filtrado(String titulo, Date fechaIni, Date fechaFin) throws ParseException {
        List<Evento> filtrados = new ArrayList<>();

        if(!titulo.equals("") && fechaIni != null && fechaFin != null){//Filtrado completo
            filtrados = this.eventoRepository.findEventoByTituloAndFechaAfterAndFechaBefore(titulo, fechaIni, fechaFin);
        } else if(titulo.equals("") && fechaIni != null && fechaFin != null){// Solo Fechas
            filtrados = this.eventoRepository.findEventoByFechaAfterAndFechaBefore(fechaIni, fechaFin);
        }

        return this.toListaDTO(filtrados);
    }

    public EventoDTO findEventobyId(Integer id) throws ParseException {
        Optional<Evento> eventoOpt = this.eventoRepository.findById(id);
        if(eventoOpt.isPresent()){
            Evento evento = eventoOpt.get();
            return evento.getDTO();
        }
        return null;
    }

    public void remove(Integer id) {
        Optional<Evento> eventoOpt = this.eventoRepository.findById(id);
        if(eventoOpt.isPresent()){
            Evento evento = eventoOpt.get();
            Usuario usuario = evento.getUsuarioByCreador();
            usuario.getEventosById().remove(evento);
            List<Entrada> entradas = (List<Entrada>) evento.getEntradasById();
            this.usuarioService.updateUsuario(usuario);
            this.entradaService.removeAllFromList(entradas);
            this.eventoRepository.delete(evento);
        }
    }

    public void save(EventoDTO eventoDTO, UsuarioDTO creador) throws ParseException {
        Evento evento;
        if(eventoDTO.getId() == null){
            evento = new Evento();
        } else {
            evento = this.eventoRepository.findById(eventoDTO.getId()).orElse(new Evento());
        }
        evento.setAforo(eventoDTO.getAforo());
        evento.setAsientosfila(eventoDTO.getAsientosfila());
        evento.setCiudad(eventoDTO.getCiudad());
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setFecha(eventoDTO.getFecha());
        evento.setFechacompra(eventoDTO.getFechacompra());
        evento.setMaxentradasusuario(eventoDTO.getMaxentradasusuario());
        evento.setNumfilas(eventoDTO.getNumfilas());
        evento.setPrecio(eventoDTO.getPrecio());
        evento.setTitulo(eventoDTO.getTitulo());
        Usuario usuario = this.usuarioService.findByUsuario(creador);
        usuario.getEventosById().add(evento);
        evento.setUsuarioByCreador(usuario);
        this.eventoRepository.save(evento);
        this.usuarioService.updateUsuario(usuario);
    }
}

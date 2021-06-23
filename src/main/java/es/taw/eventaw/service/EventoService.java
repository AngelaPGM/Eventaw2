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

    public List<EventoDTO> findAll() throws ParseException {
        List<Evento> listaEvento = this.eventoRepository.findAll();
        return this.listaEventosToDTO(listaEvento);
    }

    public List<EventoDTO>  findEventosFuturos() throws ParseException {
        List<Evento> eventosFuturos = this.eventoRepository.findEventoByFechaAfter(new Date());
        return this.listaEventosToDTO(eventosFuturos);
    }

    protected List<EventoDTO> listaEventosToDTO(List<Evento> lista) throws ParseException {
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

        return this.listaEventosToDTO(filtrados);
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

    public List<Integer> getEntradasPuedeComprar(Integer eventoId, UsuarioDTO userDTO) {
        Evento evento = this.eventoRepository.findById(eventoId).orElse(new Evento());
        Usuario usuario = this.usuarioService.findByUsuario(userDTO);
        List<Entrada> entradastotales = (List<Entrada>) usuario.getUsuarioeventosById().getEntradasById();
        int entradasLibres = evento.getAforo() - evento.getEntradasById().size();
        int entradasUsuario = 0;

        for(Entrada e : entradastotales){
            if(e.getEventoByEvento().equals(evento)){
                entradasUsuario++;
            }
        }

        int puedoComprar = evento.getMaxentradasusuario() - entradasUsuario;

        if(entradasLibres < puedoComprar){
            puedoComprar = entradasLibres;
        }

        List<Integer> lista = new ArrayList<>();
        for(int i = 1; i <= puedoComprar; i++){
            lista.add(i);
        }
        return lista;
    }

    public void tramitarEntradas(UsuarioDTO usuarioDTO, EventoDTO eventoDTO, Integer numEntradas) {
        Usuario usuario = this.usuarioService.findByUsuario(usuarioDTO);
        Evento evento = this.eventoRepository.findById(eventoDTO.getId()).orElse(new Evento());

        List<String> asientos = new ArrayList();

        for (int i = 0; i < numEntradas; i++) {
            Entrada entrada = new Entrada();

            entrada.setUsuarioeventoByUsuario(usuario.getUsuarioeventosById());
            entrada.setEventoByEvento(evento);

            if (evento.getAsientosfila() != null && evento.getNumfilas() != null) {
                String asientoSeleccionado = asientos.get(i);
                String[] partes = asientoSeleccionado.split(" ");
                String fila = partes[1];
                String asiento = partes[3];

                entrada.setNumfila(new Integer(fila));
                entrada.setAsientofila(new Integer(asiento));
            }

            this.entradaService.save(entrada);

            usuario.getUsuarioeventosById().getEntradasById().add(entrada);
            evento.getEntradasById().add(entrada);
        }

        this.eventoRepository.save(evento);
        this.usuarioService.updateUsuario(usuario);
    }
}

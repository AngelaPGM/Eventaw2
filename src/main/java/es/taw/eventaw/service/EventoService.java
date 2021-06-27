package es.taw.eventaw.service;

import es.taw.eventaw.dao.EventoEtiquetaRepository;
import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.dto.EtiquetaDTO;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventoService {
    private EventoRepository eventoRepository;
    private UsuarioService usuarioService;
    private EntradaService entradaService;
    private EtiquetaService etiquetaService;
    private EventoEtiquetaRepository eventoEtiquetaRepository;
    private EventoEtiquetaService eventoEtiquetaService;

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

    @Autowired
    public void setEtiquetaService(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @Autowired
    public void setEventoEtiquetaRepository(EventoEtiquetaRepository eventoEtiquetaRepository) {
        this.eventoEtiquetaRepository = eventoEtiquetaRepository;
    }

    @Autowired
    public void setEventoEtiquetaService(EventoEtiquetaService eventoEtiquetaService) {
        this.eventoEtiquetaService = eventoEtiquetaService;
    }

    public List<EventoDTO> findAll() throws ParseException {
        List<Evento> listaEvento = this.eventoRepository.findAll();
        return this.listaEventosToDTO(listaEvento);
    }

    public List<EventoDTO> findEventosFuturos() throws ParseException {
        List<Evento> eventosFuturos = this.eventoRepository.findEventoByFechaAfter(new Date(System.currentTimeMillis()));
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

        if (!titulo.equals("") && fechaIni != null && fechaFin != null) {//Filtrado completo
            filtrados = this.eventoRepository.findEventoByTituloAndFechaAfterAndFechaBefore(titulo, fechaIni, fechaFin);
        } else if (titulo.equals("") && fechaIni != null && fechaFin != null) {// Solo Fechas
            filtrados = this.eventoRepository.findEventoByFechaAfterAndFechaBefore(fechaIni, fechaFin);
        }

        return this.listaEventosToDTO(filtrados);
    }

    public EventoDTO findEventobyId(Integer id) throws ParseException {
        Optional<Evento> eventoOpt = this.eventoRepository.findById(id);
        if (eventoOpt.isPresent()) {
            Evento evento = eventoOpt.get();
            EventoDTO eventoDTO = evento.getDTO();

            /*List<String> etiquetas = new ArrayList<>();
            for(EventoEtiqueta eve: evento.getEventoEtiquetasById()) {
                etiquetas.add(eve.getEtiquetaByEtiqueta().getNombre());
            }
            eventoDTO.setEtiquetasSeleccionadas(etiquetas);*/
            return evento.getDTO();
        }
        return null;
    }

    public void remove(Integer id) {
        Optional<Evento> eventoOpt = this.eventoRepository.findById(id);
        if (eventoOpt.isPresent()) {
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
        if (eventoDTO.getId() == null) {
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

        List<String> listaEtiquetas = eventoDTO.getEtiquetasString();
        if (eventoDTO.getNuevaEtiqueta() != null && !eventoDTO.getNuevaEtiqueta().equals("")) {
            this.etiquetaService.nuevaEtiqueta(eventoDTO.getNuevaEtiqueta());
            listaEtiquetas.add(eventoDTO.getNuevaEtiqueta());

        }

        evento.setEventoEtiquetasById(this.eventoEtiquetaService.guardarEtiquetas(evento, listaEtiquetas));



        /*List<EventoEtiqueta> eventoEtiquetas = new ArrayList<>();
        Etiqueta etiqueta;
        EventoEtiqueta eventoEtiqueta;
        for (String  s : eventoDTO.getEtiquetasString()) {
            etiqueta = this.etiquetaService.findByNombre(s);
            eventoEtiqueta = new EventoEtiqueta();
            eventoEtiqueta.setEventoByEvento(evento);
            eventoEtiqueta.setEtiquetaByEtiqueta(etiqueta);
            eventoEtiquetas.add(eventoEtiqueta);
            this.eventoEtiquetaRepository.save(eventoEtiqueta);
        }



        evento.setEventoEtiquetasById(eventoEtiquetas);*/
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

        for (Entrada e : entradastotales) {
            if (e.getEventoByEvento().equals(evento)) {
                entradasUsuario++;
            }
        }

        int puedoComprar = evento.getMaxentradasusuario() - entradasUsuario;

        if (entradasLibres < puedoComprar) {
            puedoComprar = entradasLibres;
        }

        List<Integer> lista = new ArrayList<>();
        for (int i = 1; i <= puedoComprar; i++) {
            lista.add(i);
        }
        return lista;
    }

    public String tramitarEntradas(UsuarioDTO usuarioDTO, EventoDTO eventoDTO, Integer numEntradas) {
        Usuario usuario = this.usuarioService.findByUsuario(usuarioDTO);
        String asientosString = eventoDTO.getDescripcion();
        Evento evento = this.eventoRepository.findById(eventoDTO.getId()).orElse(new Evento());
        String error = "";

        List<String> asientos = new ArrayList();
        String[] asientosSeparados = asientosString.split(",");

        for (int i = 0; i < asientosSeparados.length; i++) {
            asientos.add(asientosSeparados[i]);
        }

        for (int i = 0; i < asientos.size(); i++) {
            String aux = asientos.get(i);
            for (int j = i + 1; j < asientos.size(); j++) {
                if (aux.equals(asientos.get(j))) {
                    error = "No puede seleccionar el mismo asiento varias veces";
                }
            }
        }

        if (error.equals("")) {
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

        return error;
    }

    public List<String> getAsientos(Integer eventoId) throws ParseException {
        Evento evento = this.eventoRepository.getById(eventoId);
        EventoDTO eventoDTO = evento.getDTO();
        List<Entrada> entradasReservadas = (List<Entrada>) evento.getEntradasById();
        List<String> asientosLista = new ArrayList<>();
        Map<Integer, List<Integer>> asientos = new TreeMap();

        for (int i = 1; i <= eventoDTO.getNumfilas(); i++) {
            List<Integer> aux = new ArrayList();
            for (int j = 1; j <= eventoDTO.getAsientosfila(); j++) {
                aux.add(j);
            }
            asientos.put(i, aux);
        }

        for (EntradaDTO e : this.entradaService.listaToDto(entradasReservadas)) {
            asientos.get(e.getNumfila()).remove(e.getAsientofila());
        }

        for (Integer j : asientos.keySet()) {
            for (Integer k : asientos.get(j)) {
                asientosLista.add("Fila: " + j + " Asiento: " + k);
            }
        }

        return asientosLista;
    }

    public List<EventoDTO> filtradoCreador(Integer id, String titulo, Date fechaIni, Date fechaFin) throws ParseException {
        List<Evento> filtrados = new ArrayList<>();

        if (!titulo.equals("") && fechaIni != null && fechaFin != null) {//Filtrado completo
            filtrados = this.eventoRepository.findEventoByCreadorAndTituloAndFechaAfterAndFechaBefor(id, titulo, fechaIni, fechaFin);
        } else if (titulo.equals("") && fechaIni != null && fechaFin != null) {// Solo Fechas
            filtrados = this.eventoRepository.findEventoByCreadorAndFechaAfterAndFechaBefore(id, fechaIni, fechaFin);
        }

        return this.listaEventosToDTO(filtrados);
    }
}

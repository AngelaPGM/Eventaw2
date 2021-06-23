package es.taw.eventaw.service;

import es.taw.eventaw.dao.EntradaRepository;
import es.taw.eventaw.dto.AnalisisDTO;
import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Entrada;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class EntradaService {
    private EntradaRepository entradaRepository;
    private UsuarioService usuarioService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setEntradaRepository(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    public List<EntradaDTO> listaToDto(List<Entrada> lista) throws ParseException {
        if(lista == null){
            return null;
        }else{
            List<EntradaDTO> listaDto = new ArrayList<>();
            for(Entrada e: lista){
                listaDto.add(e.getDTO());
            }
            return listaDto;
        }
    }

    public List<EntradaDTO> findAll() throws ParseException {
        return listaToDto(this.entradaRepository.findAll());
    }

    private List<Entrada> tamiz(List<Entrada> a, List<Entrada> filtro){
        List<Entrada> aux = new ArrayList<>();
        for(Entrada e: a){
            if(filtro.contains(e)) aux.add(e);
        }
        return aux;
    }

    public List<EntradaDTO> findByAnalisis(AnalisisDTO analisisDTO) throws ParseException {
        List<Entrada> listaEntradas = null;

        boolean activo = false;

        if(analisisDTO.getFechamayor() != null){
            activo = true;
            listaEntradas = this.entradaRepository.findEntradasFechaMayor(analisisDTO.getFechamayor());
        }
        if(analisisDTO.getFechamenor() != null){
            if(!activo){
                activo = true;
                listaEntradas = this.entradaRepository.findEntradasFechaMenor(analisisDTO.getFechamenor());
            }else{
                listaEntradas = tamiz(listaEntradas, this.entradaRepository.findEntradasFechaMenor(analisisDTO.getFechamenor()) );
            }
        }
        if(analisisDTO.getPreciomayor() != null){
            if(!activo){
                activo = true;
                listaEntradas = this.entradaRepository.findEntradasPrecioMayor(analisisDTO.getPreciomayor());
            }else{
                listaEntradas = tamiz(listaEntradas, this.entradaRepository.findEntradasPrecioMayor(analisisDTO.getPreciomayor()) );
            }
        }
        if(analisisDTO.getPreciomenor() != null){
            if(!activo){
                activo = true;
                listaEntradas = this.entradaRepository.findEntradasPrecioMenor(analisisDTO.getPreciomenor());
            }else{
                listaEntradas = tamiz(listaEntradas, this.entradaRepository.findEntradasPrecioMenor(analisisDTO.getPreciomenor()) );
            }
        }
        if(analisisDTO.getNacimientomayor() != null){
            if(!activo){
                activo = true;
                listaEntradas = this.entradaRepository.findEntradasEdadMayor(analisisDTO.getNacimientomayor());
            }else{
                listaEntradas = tamiz(listaEntradas, this.entradaRepository.findEntradasEdadMayor(analisisDTO.getNacimientomayor()) );
            }
        }
        if(analisisDTO.getNacimientomenor() != null){
            if(!activo){
                activo = true;
                listaEntradas = this.entradaRepository.findEntradasEdadMenor(analisisDTO.getNacimientomenor());
            }else{
                listaEntradas = tamiz(listaEntradas, this.entradaRepository.findEntradasEdadMenor(analisisDTO.getNacimientomenor()) );
            }
        }
        if(analisisDTO.getSexo() != null){
            if(!activo){
                activo = true;
                listaEntradas = this.entradaRepository.findEntradasSexo(analisisDTO.getSexo());
            }else{
                listaEntradas = tamiz(listaEntradas, this.entradaRepository.findEntradasSexo(analisisDTO.getSexo()) );
            }
        }
        if(!activo){    //Ningun filtro activo, mostramos todos
            listaEntradas = this.entradaRepository.findAll();
        }
        return listaToDto(listaEntradas);
    }

    public List<EntradaDTO> getEntradasFuturas(UsuarioDTO userDTO) throws ParseException {
        Usuario usuario = this.usuarioService.findByUsuario(userDTO);
        List<Entrada> entradasfuturas = this.entradaRepository.findEntradaByUsuarioeventoAndEventoByEventoAfter(usuario.getUsuarioeventosById().getId(), new Date(System.currentTimeMillis()));
        return this.listaToDto(entradasfuturas);
    }

    public List<EntradaDTO> getEntradasPasadas(UsuarioDTO userDTO) throws ParseException {
        Date today = new Date(System.currentTimeMillis());
        Integer id = userDTO.getUsuarioeventoDTOById().getId();
        List<Entrada> entradasPasadas = this.entradaRepository.findEntradaByUsuarioeventoAndEventoByEventoBefore(id, today);
        return this.listaToDto(entradasPasadas);
    }

    public void removeAllFromList(List<Entrada> entradas) {
        for(Entrada e : entradas){
            this.entradaRepository.delete(e);
        }
    }

    public void save(Entrada entrada) {
        this.entradaRepository.save(entrada);
    }

    public List<EntradaDTO> filtrado(EventoDTO eventoDTO, UsuarioDTO userDTO) throws ParseException {
        List<Entrada> filtradas =  new ArrayList<>();
        if(!eventoDTO.getTitulo().equals("") && !eventoDTO.getFecha().equals("") && !eventoDTO.getFechacompra().equals("")){ //Filtrado Completo
            filtradas = this.entradaRepository.findEntradaByEvento(userDTO.getUsuarioeventoDTOById().getId(), eventoDTO.getTitulo(),
                    eventoDTO.getFecha(), eventoDTO.getFechacompra());
        } else if(eventoDTO.getTitulo().equals("") &&  !eventoDTO.getFecha().equals("") && !eventoDTO.getFechacompra().equals("")) {// Solo Fechas
            filtradas = this.entradaRepository.findEntradaByEventoSoloFechas(userDTO.getUsuarioeventoDTOById().getId(), eventoDTO.getFecha(), eventoDTO.getFechacompra());
        }
        return this.listaToDto(filtradas);
    }
}

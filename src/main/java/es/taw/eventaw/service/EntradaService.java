package es.taw.eventaw.service;

import es.taw.eventaw.dao.EntradaRepository;
import es.taw.eventaw.dto.AnalisisDTO;
import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Entrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EntradaService {
    private EntradaRepository entradaRepository;

    @Autowired
    public void setEntradaRepository(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    private List<EntradaDTO> listaToDto(List<Entrada> lista){
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

    public List<EntradaDTO> findByAnalisis(AnalisisDTO analisisDTO){
        List<Entrada> listaEntradas = null; //this.entradaRepository.findEntradasByAnalisis(analisisDTO.getFechamayor());

        if(analisisDTO.getFechamayor() != null){
            List<Entrada> listaFechaMayor = this.entradaRepository.findEntradasFechaMayor(analisisDTO.getFechamayor());
        }
        if(analisisDTO.getFechamenor() != null){
            List<Entrada> listaFechaMenor = this.entradaRepository.findEntradasFechaMenor(analisisDTO.getFechamenor());
        }
        if(analisisDTO.getPreciomayor() != null){
            List<Entrada> listaPrecioMayor = this.entradaRepository.findEntradasPrecioMayor(analisisDTO.getPreciomayor());
        }
        if(analisisDTO.getPreciomenor() != null){
            List<Entrada> listaPrecioMenor = this.entradaRepository.findEntradasPrecioMenor(analisisDTO.getPreciomenor());
        }
        return listaToDto(listaEntradas);
    }

    public List<EntradaDTO> getEntradasFuturas(UsuarioDTO userDTO) {
        List<Entrada> entradasfuturas = this.entradaRepository.findEntradaByUsuarioeventoByUsuarioAndEventoByEventoAfter(userDTO.getId(), new Date());
        return this.listaToDto(entradasfuturas);
    }

    public List<EntradaDTO> getEntradasPasadas(UsuarioDTO userDTO) {
        List<Entrada> entradasPasadas = this.entradaRepository.findEntradaByUsuarioeventoByUsuarioAndEventoByEventoBefore(userDTO.getId(), new Date());
        return this.listaToDto(entradasPasadas);
    }
}

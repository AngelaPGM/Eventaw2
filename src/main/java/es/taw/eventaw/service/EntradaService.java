package es.taw.eventaw.service;

import es.taw.eventaw.dao.EntradaRepository;
import es.taw.eventaw.dto.AnalisisDTO;
import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.entity.Entrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Entrada> listaEntradas = this.entradaRepository.findEntradasByAnalisis(analisisDTO);
        return listaToDto(listaEntradas);
    }
}

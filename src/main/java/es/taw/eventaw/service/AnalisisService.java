package es.taw.eventaw.service;

import es.taw.eventaw.dao.AnalisisRepository;
import es.taw.eventaw.dto.AnalisisDTO;
import es.taw.eventaw.entity.Analisis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalisisService {
    private AnalisisRepository analisisRepository;

    @Autowired
    public void setAnalisisRepository(AnalisisRepository analisisRepository) {
        this.analisisRepository = analisisRepository;
    }

    private List<AnalisisDTO> listaToDto(List<Analisis> lista){
        if(lista == null){
            return null;
        }else{
            List<AnalisisDTO> listaDto = new ArrayList<>();
            for(Analisis a: lista){
                listaDto.add(a.getDTO());
            }
            return listaDto;
        }
    }

    public List<AnalisisDTO> listarAnalisis(){
        List<Analisis> listaAnalisis = this.analisisRepository.findAll();
        return listaToDto(listaAnalisis);
    }

    public AnalisisDTO findById(Integer id){
        Analisis analisis = this.analisisRepository.getById(id);
        AnalisisDTO dto = analisis.getDTO();
        return dto;
    }

    public void doGuardar(AnalisisDTO dto){
        Analisis analisis;
        if (dto.getId() == null) {
            analisis = new Analisis();
        } else {
            analisis = this.analisisRepository.findById(dto.getId()).orElse(new Analisis());
        }

        analisis.setId(dto.getId());
        analisis.setNombre(dto.getNombre());
        analisis.setFechamayor(dto.getFechamayor());
        analisis.setFechamenor(dto.getFechamenor());
        analisis.setPreciomayor(dto.getPreciomayor());
        analisis.setPreciomenor(dto.getPreciomenor());

        this.analisisRepository.save(analisis);
    }
}

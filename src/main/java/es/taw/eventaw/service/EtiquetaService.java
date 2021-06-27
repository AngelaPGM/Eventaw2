package es.taw.eventaw.service;

import es.taw.eventaw.dao.EtiquetaRepository;
import es.taw.eventaw.dto.EtiquetaDTO;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.entity.Etiqueta;
import es.taw.eventaw.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EtiquetaService {

    private EtiquetaRepository etiquetaRepository;

    @Autowired
    public void setEtiquetaRepository(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    public List<EtiquetaDTO> findAll() throws ParseException {
        return this.listaEtiquetasToDTO(this.etiquetaRepository.findAll());
    }

    public List<String> findAllString() throws ParseException {
        List<String> etiquetas = new ArrayList<>();
        List<EtiquetaDTO> listaDTO = this.findAll();

        for(EtiquetaDTO e: listaDTO) {
            etiquetas.add(e.getNombre());
        }
        return etiquetas;
    }

    protected List<EtiquetaDTO> listaEtiquetasToDTO(List<Etiqueta> lista) throws ParseException {
        List<EtiquetaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<EtiquetaDTO>();
            for (Etiqueta e : lista) {
                listaDTO.add(e.getDTO());
            }
        }
        return listaDTO;
    }

    public Etiqueta findById(Integer id) {
        return this.etiquetaRepository.findById(id).orElse(new Etiqueta());
    }

    public Etiqueta findByNombre(String s) {
        return this.etiquetaRepository.findEtiquetaByNombre(s);
    }

    public void nuevaEtiqueta(String s) {
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre(s);
        this.etiquetaRepository.save(etiqueta);
    }


}

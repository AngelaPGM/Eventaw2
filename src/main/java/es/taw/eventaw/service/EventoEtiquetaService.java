package es.taw.eventaw.service;

import es.taw.eventaw.dao.EventoEtiquetaRepository;
import es.taw.eventaw.entity.Etiqueta;
import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.EventoEtiqueta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoEtiquetaService {


    private EventoEtiquetaRepository eventoEtiquetaRepository;
    private EtiquetaService etiquetaService;

    @Autowired
    public void setEventoEtiquetaRepository(EventoEtiquetaRepository eventoEtiquetaRepository) {
        this.eventoEtiquetaRepository = eventoEtiquetaRepository;
    }

    @Autowired
    public void setEtiquetaService(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    public List<EventoEtiqueta> guardarEtiquetas(Evento e, List<String> etiquetasString) {

        List<EventoEtiqueta> eventoEtiquetasList = new ArrayList<>();
        this.eventoEtiquetaRepository.deleteByEventoByEvento(e);
        for (String s : etiquetasString) {
            EventoEtiqueta eventoEtiqueta = new EventoEtiqueta();
            eventoEtiqueta.setEventoByEvento(e);
            eventoEtiqueta.setEtiquetaByEtiqueta(this.etiquetaService.findByNombre(s));
            this.eventoEtiquetaRepository.save(eventoEtiqueta);
            eventoEtiquetasList.add(eventoEtiqueta);
        }
        return eventoEtiquetasList;
    }

}

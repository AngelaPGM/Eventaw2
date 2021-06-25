package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Etiqueta;
import es.taw.eventaw.entity.Evento;

public class EventoEtiquetaDTO {
    private Integer id;
    private EtiquetaDTO etiquetaDTOByEtiqueta;

    public EventoEtiquetaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public EtiquetaDTO getEtiquetaDTOByEtiqueta() {
        return etiquetaDTOByEtiqueta;
    }

    public void setEtiquetaDTOByEtiqueta(EtiquetaDTO etiquetaDTOByEtiqueta) {
        this.etiquetaDTOByEtiqueta = etiquetaDTOByEtiqueta;
    }
}

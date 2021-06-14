package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Etiqueta;
import es.taw.eventaw.entity.Evento;

public class EventoEtiquetaDTO {
    private Integer id;
    private Evento eventoByEvento;
    private Etiqueta etiquetaByEtiqueta;

    public EventoEtiquetaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evento getEventoByEvento() {
        return eventoByEvento;
    }

    public void setEventoByEvento(Evento eventoByEvento) {
        this.eventoByEvento = eventoByEvento;
    }

    public Etiqueta getEtiquetaByEtiqueta() {
        return etiquetaByEtiqueta;
    }

    public void setEtiquetaByEtiqueta(Etiqueta etiquetaByEtiqueta) {
        this.etiquetaByEtiqueta = etiquetaByEtiqueta;
    }
}

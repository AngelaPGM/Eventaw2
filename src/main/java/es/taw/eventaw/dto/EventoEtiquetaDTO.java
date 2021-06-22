package es.taw.eventaw.dto;


public class EventoEtiquetaDTO {
    private Integer id;
    private EventoDTO eventoDTOByEvento;
    private EtiquetaDTO etiquetaDTOByEtiqueta;

    public EventoEtiquetaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventoDTO getEventoDTOByEvento() {
        return eventoDTOByEvento;
    }

    public void setEventoDTOByEvento(EventoDTO eventoDTOByEvento) {
        this.eventoDTOByEvento = eventoDTOByEvento;
    }

    public EtiquetaDTO getEtiquetaDTOByEtiqueta() {
        return etiquetaDTOByEtiqueta;
    }

    public void setEtiquetaDTOByEtiqueta(EtiquetaDTO etiquetaDTOByEtiqueta) {
        this.etiquetaDTOByEtiqueta = etiquetaDTOByEtiqueta;
    }
}

package es.taw.eventaw.dto;

public class EntradaDTO {
    private Integer id;
    private Integer numfila;
    private Integer asientofila;
    private UsuarioeventoDTO usuarioeventoDTOByUsuario;
    private EventoDTO eventoDTOByEvento;

    public EntradaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumfila() {
        return numfila;
    }

    public void setNumfila(Integer numfila) {
        this.numfila = numfila;
    }

    public Integer getAsientofila() {
        return asientofila;
    }

    public void setAsientofila(Integer asientofila) {
        this.asientofila = asientofila;
    }

    public UsuarioeventoDTO getUsuarioeventoDTOByUsuario() {
        return usuarioeventoDTOByUsuario;
    }

    public void setUsuarioeventoDTOByUsuario(UsuarioeventoDTO usuarioeventoDTOByUsuario) {
        this.usuarioeventoDTOByUsuario = usuarioeventoDTOByUsuario;
    }

    public EventoDTO getEventoDTOByEvento() {
        return eventoDTOByEvento;
    }

    public void setEventoDTOByEvento(EventoDTO eventoDTOByEvento) {
        this.eventoDTOByEvento = eventoDTOByEvento;
    }
}

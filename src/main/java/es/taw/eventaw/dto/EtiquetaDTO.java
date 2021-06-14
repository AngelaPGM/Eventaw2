package es.taw.eventaw.dto;

import es.taw.eventaw.entity.EventoEtiqueta;

import java.util.Collection;

public class EtiquetaDTO {
    private Integer id;
    private String nombre;
    private Collection<EventoEtiqueta> eventoEtiquetasById;

    public EtiquetaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<EventoEtiqueta> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(Collection<EventoEtiqueta> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }
}

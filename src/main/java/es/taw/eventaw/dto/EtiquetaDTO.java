package es.taw.eventaw.dto;


import java.util.Collection;

public class EtiquetaDTO {
    private Integer id;
    private String nombre;
    private Collection<EventoEtiquetaDTO> eventoEtiquetasDTOById;

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

    public Collection<EventoEtiquetaDTO> getEventoEtiquetasDTOById() {
        return eventoEtiquetasDTOById;
    }

    public void setEventoEtiquetasDTOById(Collection<EventoEtiquetaDTO> eventoEtiquetasDTOById) {
        this.eventoEtiquetasDTOById = eventoEtiquetasDTOById;
    }
}

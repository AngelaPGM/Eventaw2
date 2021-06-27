package es.taw.eventaw.dto;

import es.taw.eventaw.entity.EventoEtiqueta;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class EtiquetaDTO {
    private Integer id;
    private String nombre;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtiquetaDTO that = (EtiquetaDTO) o;
        return id.equals(that.id) && nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}

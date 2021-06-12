package es.taw.eventaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Etiqueta {
    private Integer id;
    private String nombre;
    private Collection<EventoEtiqueta> eventoEtiquetasById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 45)
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
        Etiqueta etiqueta = (Etiqueta) o;
        return Objects.equals(id, etiqueta.id) && Objects.equals(nombre, etiqueta.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @OneToMany(mappedBy = "etiquetaByEtiqueta")
    public Collection<EventoEtiqueta> getEventoEtiquetasById() {
        return eventoEtiquetasById;
    }

    public void setEventoEtiquetasById(Collection<EventoEtiqueta> eventoEtiquetasById) {
        this.eventoEtiquetasById = eventoEtiquetasById;
    }
}

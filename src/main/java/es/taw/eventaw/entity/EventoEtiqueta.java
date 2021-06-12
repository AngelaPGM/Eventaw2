package es.taw.eventaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EVENTO_ETIQUETA", schema = "SAMPLETAWBD", catalog = "")
public class EventoEtiqueta {
    private Integer id;
    private Evento eventoByEvento;
    private Etiqueta etiquetaByEtiqueta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoEtiqueta that = (EventoEtiqueta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "EVENTO", referencedColumnName = "ID", nullable = false)
    public Evento getEventoByEvento() {
        return eventoByEvento;
    }

    public void setEventoByEvento(Evento eventoByEvento) {
        this.eventoByEvento = eventoByEvento;
    }

    @ManyToOne
    @JoinColumn(name = "ETIQUETA", referencedColumnName = "ID", nullable = false)
    public Etiqueta getEtiquetaByEtiqueta() {
        return etiquetaByEtiqueta;
    }

    public void setEtiquetaByEtiqueta(Etiqueta etiquetaByEtiqueta) {
        this.etiquetaByEtiqueta = etiquetaByEtiqueta;
    }
}

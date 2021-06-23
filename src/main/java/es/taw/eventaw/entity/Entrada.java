package es.taw.eventaw.entity;

import es.taw.eventaw.dto.EntradaDTO;

import javax.persistence.*;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Entity
public class Entrada {
    private Integer id;
    private Integer numfila;
    private Integer asientofila;
    private Usuarioevento usuarioeventoByUsuario;
    private Evento eventoByEvento;

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
    @Column(name = "NUMFILA", nullable = true)
    public Integer getNumfila() {
        return numfila;
    }

    public void setNumfila(Integer numfila) {
        this.numfila = numfila;
    }

    @Basic
    @Column(name = "ASIENTOFILA", nullable = true)
    public Integer getAsientofila() {
        return asientofila;
    }

    public void setAsientofila(Integer asientofila) {
        this.asientofila = asientofila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return Objects.equals(id, entrada.id) && Objects.equals(numfila, entrada.numfila) && Objects.equals(asientofila, entrada.asientofila);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numfila, asientofila);
    }

    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    public Usuarioevento getUsuarioeventoByUsuario() {
        return usuarioeventoByUsuario;
    }

    public void setUsuarioeventoByUsuario(Usuarioevento usuarioeventoByUsuario) {
        this.usuarioeventoByUsuario = usuarioeventoByUsuario;
    }

    @ManyToOne
    @JoinColumn(name = "EVENTO", referencedColumnName = "ID", nullable = false)
    public Evento getEventoByEvento() {
        return eventoByEvento;
    }

    public void setEventoByEvento(Evento eventoByEvento) {
        this.eventoByEvento = eventoByEvento;
    }

    @Transient
    public EntradaDTO getDTO() throws ParseException {
        EntradaDTO dto = new EntradaDTO();
        dto.setId(id);
        dto.setNumfila(numfila);
        dto.setAsientofila(asientofila);
        dto.setEventoDTO(this.eventoByEvento.getDTO());
        List<EntradaDTO> aux = dto.getEventoDTO().getEntradasDTO();
        aux.add(dto);
        dto.getEventoDTO().setEntradasDTO(aux);
        //dto.setUsuarioeventoDTO(this.usuarioeventoByUsuario.getDTO());
        return dto;
    }
}

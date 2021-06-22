package es.taw.eventaw.entity;

import es.taw.eventaw.dto.ConversacionDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Conversacion {
    private Integer id;
    private Usuario usuarioByTeleoperador;
    private Usuario usuarioByUsuario;
    private Collection<Mensaje> mensajesById;

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
        Conversacion that = (Conversacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "TELEOPERADOR", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByTeleoperador() {
        return usuarioByTeleoperador;
    }

    public void setUsuarioByTeleoperador(Usuario usuarioByTeleoperador) {
        this.usuarioByTeleoperador = usuarioByTeleoperador;
    }

    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }

    @OneToMany(mappedBy = "conversacionByConversacion")
    public Collection<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }

    @Transient
    public ConversacionDTO getDTO() {
        ConversacionDTO dto = new ConversacionDTO();
        dto.setId(this.id);
        dto.setUsuarioDTOByTeleoperador(this.getDTO().getUsuarioDTOByTeleoperador());
        dto.setUsuarioDTOByUsuario(this.getDTO().getUsuarioDTOByUsuario());
        dto.setMensajesDTOById(this.getDTO().getMensajesDTOById());
        return dto;
    }
}

package es.taw.eventaw.entity;

import es.taw.eventaw.dto.MensajeDTO;
import es.taw.eventaw.dto.UsuarioDTO;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {
    private Integer id;
    private String correo;
    private String contrasenya;
    private Collection<Conversacion> conversacionsById;
    private Collection<Conversacion> conversacionsById_0;
    private Collection<Evento> eventosById;
    private Collection<Mensaje> mensajesById;
    private Rol rolByRol;
    private Usuarioevento usuarioeventosById;

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
    @Column(name = "CORREO", nullable = false, length = 45)
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "CONTRASENYA", nullable = false, length = 45)
    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(correo, usuario.correo) && Objects.equals(contrasenya, usuario.contrasenya);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, correo, contrasenya);
    }

    @OneToMany(mappedBy = "usuarioByTeleoperador")
    public Collection<Conversacion> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<Conversacion> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    @OneToMany(mappedBy = "usuarioByUsuario")
    public Collection<Conversacion> getConversacionsById_0() {
        return conversacionsById_0;
    }

    public void setConversacionsById_0(Collection<Conversacion> conversacionsById_0) {
        this.conversacionsById_0 = conversacionsById_0;
    }

    @OneToMany(mappedBy = "usuarioByCreador")
    public Collection<Evento> getEventosById() {
        return eventosById;
    }

    public void setEventosById(Collection<Evento> eventosById) {
        this.eventosById = eventosById;
    }

    @OneToMany(mappedBy = "usuarioByEmisor")
    public Collection<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }

    @ManyToOne
    @JoinColumn(name = "ROL", referencedColumnName = "ID", nullable = false)
    public Rol getRolByRol() {
        return rolByRol;
    }

    public void setRolByRol(Rol rolByRol) {
        this.rolByRol = rolByRol;
    }

    @OneToOne(mappedBy = "usuarioByIdusuario")
    public Usuarioevento getUsuarioeventosById() {
        return usuarioeventosById;
    }

    public void setUsuarioeventosById(Usuarioevento usuarioeventosById) {
        this.usuarioeventosById = usuarioeventosById;
    }

    @Transient
    public UsuarioDTO getDTO() throws ParseException {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(this.id);
        dto.setCorreo(this.correo);
        dto.setContrasenya(this.contrasenya);
        dto.setRolDTOByRol(this.rolByRol.getDTO());
        dto.setMensajesDTO((List<Mensaje>)this.mensajesById);
        if(dto.getRolDTOByRol().getId() == 2){
            if(this.usuarioeventosById != null) {
                dto.setUsuarioeventoDTOById(this.usuarioeventosById.getDTO());
                dto.getUsuarioeventoDTOById().setUsuarioDTO(dto);
            }
        }
        return dto;
    }
}

package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Entrada;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioeventoDTO {
    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String domicilio;
    private String ciudad;
    private Date fechanacimiento;
    private String sexo;
    private UsuarioDTO usuarioDTO;
    private List<EntradaDTO> entradasDTO;

    public UsuarioeventoDTO() {
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public List<EntradaDTO> getEntradasDTO() {
        return entradasDTO;
    }

    public void setEntradasDTO(List<Entrada> entradas) throws ParseException {
        if(entradas == null){
            this.entradasDTO = new ArrayList<>();
        }else{
            List<EntradaDTO> entradasDTO =  new ArrayList<>();
            for(Entrada e : entradas){
                entradasDTO.add(e.getDTO());
            }
            this.entradasDTO = entradasDTO;
        }
    }
}

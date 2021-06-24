package es.taw.eventaw.dto;

import es.taw.eventaw.entity.Entrada;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private String ciudad;
    private Date fecha;
    private Date fechacompra;
    private Double precio;
    private Integer aforo;
    private Integer maxentradasusuario;
    private Integer numfilas;
    private Integer asientosfila;
    private List<EntradaDTO> entradasDTO;
    private UsuarioDTO creadorDTO;

    public EventoDTO() throws ParseException {
        this.titulo = "";
        this.descripcion = "";
        this.ciudad = "";
        this.fecha = new Date(System.currentTimeMillis());
        this.fechacompra = new Date(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Integer getMaxentradasusuario() {
        return maxentradasusuario;
    }

    public void setMaxentradasusuario(Integer maxentradasusuario) {
        this.maxentradasusuario = maxentradasusuario;
    }

    public Integer getNumfilas() {
        return numfilas;
    }

    public void setNumfilas(Integer numfilas) {
        this.numfilas = numfilas;
    }

    public Integer getAsientosfila() {
        return asientosfila;
    }

    public void setAsientosfila(Integer asientosfila) {
        this.asientosfila = asientosfila;
    }

    public List<EntradaDTO> getEntradasDTO() {
        if(entradasDTO == null) return new ArrayList<>();
        return entradasDTO;
    }

    public void setEntradasDTO(List<EntradaDTO> entradasDTO){
        if(entradasDTO == null) this.entradasDTO = new ArrayList<>();
        this.entradasDTO = entradasDTO;
    }

    public UsuarioDTO getCreadorDTO() {
        return creadorDTO;
    }

    public void setCreadorDTO(UsuarioDTO creadorDTO) {
        this.creadorDTO = creadorDTO;
    }
}

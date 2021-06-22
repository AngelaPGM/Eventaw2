package es.taw.eventaw.service;

import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dao.UsuarioeventoRepository;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.dto.UsuarioeventoDTO;
import es.taw.eventaw.entity.Usuario;
import es.taw.eventaw.entity.Usuarioevento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioeventoService {

    private UsuarioeventoRepository usuarioeventoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioeventoRepository(UsuarioeventoRepository usuarioeventoRepository) {
        this.usuarioeventoRepository = usuarioeventoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void nuevoUsuarioevento(Usuario user, UsuarioeventoDTO inputDataDTO) {
        Usuarioevento usuarioevento = new Usuarioevento();
        usuarioevento.setSexo(inputDataDTO.getSexo());
        usuarioevento.setNombre(inputDataDTO.getNombre());
        usuarioevento.setFechanacimiento(inputDataDTO.getFechanacimiento());
        usuarioevento.setCiudad(inputDataDTO.getCiudad());
        usuarioevento.setDomicilio(inputDataDTO.getDomicilio());
        usuarioevento.setApellido1(inputDataDTO.getApellido1());
        usuarioevento.setApellido2(inputDataDTO.getApellido2());
        usuarioevento.setUsuarioByIdusuario(user);


        this.usuarioeventoRepository.save(usuarioevento);
        this.usuarioRepository.save(user);
    }

    public void guardarUsuarioevento(UsuarioDTO dto) {
        Usuarioevento uevento;

        if (dto.getUsuarioeventoDTOById().getId() == null) {
            uevento = new Usuarioevento();
        } else {
            uevento = this.usuarioeventoRepository.findById(dto.getUsuarioeventoDTOById().getId()).orElse(new Usuarioevento());
        }

        uevento.setId(dto.getUsuarioeventoDTOById().getId());
        uevento.setNombre(dto.getUsuarioeventoDTOById().getNombre());
        uevento.setApellido1(dto.getUsuarioeventoDTOById().getApellido1());
        uevento.setApellido2(dto.getUsuarioeventoDTOById().getApellido2());
        uevento.setDomicilio(dto.getUsuarioeventoDTOById().getDomicilio());
        uevento.setCiudad(dto.getUsuarioeventoDTOById().getCiudad());
        uevento.setFechanacimiento(dto.getUsuarioeventoDTOById().getFechanacimiento());
        uevento.setSexo(dto.getUsuarioeventoDTOById().getSexo());

       //Usuario u = this.usuarioRepository.findUsuarioById(dto.getUsuarioDTO().getId());
     // uevento.setUsuarioByIdusuario(u);

        this.usuarioeventoRepository.save(uevento);
    }
}

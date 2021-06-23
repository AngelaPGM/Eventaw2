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

    public void guardarUsuarioevento(Usuario usuario, UsuarioeventoDTO ueventoDTO) {
        Usuarioevento uevento;

        if (ueventoDTO.getId() == null) {
            uevento = new Usuarioevento();
        } else {
            uevento = this.usuarioeventoRepository.findById(ueventoDTO.getId()).orElse(new Usuarioevento());
        }

        uevento.setId(ueventoDTO.getId());
        uevento.setNombre(ueventoDTO.getNombre());
        uevento.setApellido1(ueventoDTO.getApellido1());
        uevento.setApellido2(ueventoDTO.getApellido2());
        uevento.setDomicilio(ueventoDTO.getDomicilio());
        uevento.setCiudad(ueventoDTO.getCiudad());
        uevento.setFechanacimiento(ueventoDTO.getFechanacimiento());
        uevento.setSexo(ueventoDTO.getSexo());
        uevento.setUsuarioByIdusuario(usuario);

        this.usuarioeventoRepository.save(uevento);
        this.usuarioRepository.save(usuario);
    }
}

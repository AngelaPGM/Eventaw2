package es.taw.eventaw.service;

import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private EventoService eventoService;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public UsuarioDTO comprobarCredenciales(String correo, String pass) {
        UsuarioDTO userDTO = null;
        Usuario user = this.usuarioRepository.findUsuarioByCorreoAndContrasenya(correo, pass);
        if (user != null) userDTO = user.getDTO();
        return userDTO;
    }

}

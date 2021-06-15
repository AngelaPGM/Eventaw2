package es.taw.eventaw.service;

import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

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


    public String login(Usuario usuario, Model model, HttpSession session) {
        Usuario user = this.usuarioRepository.findUsuarioByCorreoAndContrasenya(usuario.getCorreo(), usuario.getContrasenya());
        String strTo = "login";


        if (user != null) { //existe el correo
            session.setAttribute("userDTO", user.getDTO());
            switch (user.getRolByRol().getId()) {
                case 1: //Admin
                    strTo = ""; //ESCRIBIR AQUI EL REDIRECT A ADMIN
                    break;

                case 2: //Usuarioevento
                    strTo = "inicioUEvento";
                    //hacer aqui una query de que aparezcan los eventos a partir del dia de hoy
                    break;

                case 3: //Creador eventos
                    strTo = "inicioCreador";
                    model.addAttribute("misEventos", user.getEventosById());
                    model.addAttribute("todosEventos", this.eventoService.findAll());
                    break;

                case 4: //Teleoperador
                    strTo = ""; //ESCRIBIR AQUI EL REDIRECT A TELEOPERADOR
                    break;

                default: //Analista
                    strTo = ""; //ESCRIBIR AQUI EL REDIRECT A ANALISTA
                    break;
            }

        }
        return strTo;

    }
}

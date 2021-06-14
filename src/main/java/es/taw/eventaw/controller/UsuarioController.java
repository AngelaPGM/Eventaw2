package es.taw.eventaw.controller;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class UsuarioController {
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }


    @GetMapping("/")
    public String doInit(Model model) {
        Usuario user = new Usuario();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") Usuario usuario, Model model, HttpSession session) {
        Usuario user = this.usuarioRepository.findByCorreo(usuario.getCorreo());
        String errorLog = "", strTo = "";

        if (user != null) { //existe el correo
            if (user.getContrasenya().equals(usuario.getContrasenya())) { //contras coinciden
                session.setAttribute("user", user);
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
                        model.addAttribute("todosEventos", this.eventoRepository.findAll());
                        break;

                    case 4: //Teleoperador
                        strTo = ""; //ESCRIBIR AQUI EL REDIRECT A TELEOPERADOR
                        break;

                    case 5: //Analista
                        strTo = ""; //ESCRIBIR AQUI EL REDIRECT A ANALISTA
                        break;
                }
            } else {
                errorLog = "¡Contraseña incorrecta!";
                strTo = "login";
            }
        } else {
            errorLog = "¡Email incorrecto!";
            strTo = "login";
        }
        model.addAttribute("errorLog", errorLog);
        return strTo;
    }
}

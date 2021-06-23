package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Usuario;
import es.taw.eventaw.service.EventoService;
import es.taw.eventaw.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;


@Controller
public class UsuarioController {
    private UsuarioService usuarioService;
    private EventoService eventoService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }


    @GetMapping("/")
    public String doInit(Model model) {
        Usuario user = new Usuario();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") Usuario usuario, Model model, HttpSession session) throws ParseException {
        UsuarioDTO userDTO = this.usuarioService.comprobarCredenciales(usuario.getCorreo(), usuario.getContrasenya());
        String strTo = "login";

        if (userDTO == null) {
            model.addAttribute("errorLog", "Credenciales no validas.");
        } else {
            session.setAttribute("userDTO", userDTO);
            switch (userDTO.getRolDTOByRol().getId()) {
                case 1: //Admin
                    strTo = this.doInicioAdmin(model,session); //ESCRIBIR AQUI EL REDIRECT A ADMIN
                    break;

                case 2: //Usuarioevento
                    strTo = this.doInicioUEvento(model);
                    break;

                case 3: //Creador eventos
                    strTo = this.doInicioCreador(model, session);
                    break;

                case 4: //Teleoperador
                    strTo = ""; //ESCRIBIR AQUI EL REDIRECT A TELEOPERADOR
                    break;

                default: //Analista
                    strTo = "redirect:analisis/"; //ESCRIBIR AQUI EL REDIRECT A ANALISTA
                    break;
            }
        }
        return strTo;
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session, Model model) {
        session.removeAttribute("userDTO");
        return doInit(model);
    }

    @GetMapping("/inicioUEvento")
    public String doInicioUEvento(Model model) throws ParseException {
        model.addAttribute("eventosFuturos", this.eventoService.findEventosFuturos());
        model.addAttribute("eventoDTO", new EventoDTO());
        return "inicioUEvento";
    }

    @GetMapping("/inicioCreador")
    public String doInicioCreador(Model model, HttpSession session) throws ParseException {
        model.addAttribute("misEventos", this.usuarioService.getEventos((UsuarioDTO) session.getAttribute("userDTO")));
        model.addAttribute("todosEventos", this.eventoService.findAll());
        model.addAttribute("eventoDTO", new EventoDTO());
        return "inicioCreador";
    }
    @GetMapping("/inicioAdmin")
    public String doInicioAdmin(Model model, HttpSession session) throws ParseException {
        model.addAttribute("todosEventos", this.eventoService.findAll());
        model.addAttribute("todosUsuarios", this.usuarioService.findAll());
        return "inicioAdministrador";
    }
}

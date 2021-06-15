package es.taw.eventaw.controller;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.entity.Usuario;
import es.taw.eventaw.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public String doInit(Model model) {
        Usuario user = new Usuario();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") Usuario usuario, Model model, HttpSession session) {
        String strTo = this.service.login(usuario, model, session), errorLog="";
        if(strTo.equals("login")) errorLog="Datos no validos.";

        model.addAttribute("errorLog", errorLog);
        return this.service.login(usuario, model, session);
    }
}

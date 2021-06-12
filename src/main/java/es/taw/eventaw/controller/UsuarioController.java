package es.taw.eventaw.controller;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/inicio")
    public String doLogin(@ModelAttribute("user") Usuario usuario, Model model, HttpSession session) {
        Usuario user = this.usuarioRepository.findByCorreo(usuario.getCorreo());

        String jsp = "";
        String errorLog = "";
        List<Evento> eventos;
        Date today = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        if (usuario != null) {
            if (usuario.getContrasenya().equals(usuario.getContrasenya())) {
                if (usuario.getContrasenya().equals(usuario.getContrasenya())) {
                    if (null != usuario.getRolByRol().getId()) switch (usuario.getRolByRol().getId()) {
                        case 1://admin
                            jsp = "redirect:/admin";//redirect al controlador del admin
                            session.setAttribute("user", usuario);
                            break;
                        case 2://u_evento
                            jsp = "inicio";
                            session.setAttribute("user", usuario);
                            eventos = this.eventoRepository.findAll();
                            for (Evento e : this.eventoRepository.findAll()) {//SI ALGUIEN ENCUENTRA UNA QUERY PA HACER ESTO MEJOR
                                if (!formato.format(e.getFecha()).equals(formato.format(today))) {
                                    if (!e.getFecha().after(today)) eventos.remove(e);
                                }
                            }
                            model.addAttribute("eventos", eventos);
                            break;
                        case 3://c_evento
                            jsp = "inicioCreador";
                            session.setAttribute("user", usuario);
                            List<Evento> listaEventos = (List<Evento>) usuario.getEventosById();
                            model.addAttribute("eventos", listaEventos);
                            model.addAttribute("todosEventos", this.eventoRepository.findAll());
                            break;
                        case 4://teleoperador
                            jsp = "redirect:/teleoperador";//redirect al controlador del teleoperador
                            session.setAttribute("user", usuario);
                            break;
                        case 5://analista
                            jsp = "redirect:/";//redirect al controlador del analista
                            session.setAttribute("analista", usuario);
                            break;
                        default:
                            break;
                    }
                } else {
                    jsp = "login";
                    errorLog = "¡Contraseña incorrecta!";
                }
            }
        } else {
            jsp = "login";
            errorLog = "¡Email incorrecto!";
        }

        model.addAttribute("errorLog", errorLog);
        return jsp;
    }
}

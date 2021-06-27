package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.RolDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Usuario;
import es.taw.eventaw.service.ConversacionService;
import es.taw.eventaw.service.EventoService;
import es.taw.eventaw.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;


@Controller
public class UsuarioController {
    private UsuarioService usuarioService;
    private EventoService eventoService;
    private ConversacionService conversacionService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Autowired
    public void setConversacionService(ConversacionService conversacionService) {
        this.conversacionService = conversacionService;
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
                    strTo = this.doInicioAdmin(model,session);
                    break;

                case 2: //Usuarioevento
                    strTo = this.doInicioUEvento(model);
                    break;

                case 3: //Creador eventos
                    strTo = this.doInicioCreador(model, session);
                    break;

                case 4: //Teleoperador
                    strTo = this.doInicioTeleoperador(model,session);
                    break;

                default: //Analista
                    strTo = "redirect:analisis/";
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
        model.addAttribute("eventosFuturos", this.eventoService.findAll());
        model.addAttribute("usuarios", this.usuarioService.findAll());
        model.addAttribute("eventoDTO", new EventoDTO());
        model.addAttribute("usuarioFiltroDTO", new UsuarioDTO());
        return "inicioAdministrador";
    }

    @GetMapping("/inicioTeleoperador")
    public String doInicioTeleoperador(Model model, HttpSession session) throws ParseException {
        model.addAttribute("todosChat", this.conversacionService.getChatsDto());
        model.addAttribute("userDto",session.getAttribute("userDTO"));
        return "teleoperador";
    }


    @PostMapping("/filtrar")
    public String doFiltrarUsuarios(@ModelAttribute("usuarioFiltroDTO") UsuarioDTO inputData, Model model, HttpSession session) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        if(usuarioDTO.getRolDTOByRol().getId() == 1){ //AdministradorFiltradoUsuario
        List<UsuarioDTO> usuariosFiltrados = this.usuarioService.filtradoUsuario(inputData.getCorreo(),inputData.getContrasenya2());
        model.addAttribute("usuarios",usuariosFiltrados);
        model.addAttribute("usuarioFiltroDTO", new UsuarioDTO());
        model.addAttribute("eventoDTO", new EventoDTO());
        model.addAttribute("eventosFuturos",this.eventoService.findAll());
        return "inicioAdministrador";
        }else{
            return "";
        }

    }

    @GetMapping("/editar/{id}")
    public String cargarEditar(@PathVariable("id") Integer id, Model model) throws ParseException {
        UsuarioDTO usuario = this.usuarioService.findUsuarioEventobyId(id);

        model.addAttribute("userDTO",usuario);
        //model.addAttribute("listaRolDTO",this.usuarioService.findAllRol());
        return "perfilUsuario";
    }

    @GetMapping("/crear")
    public String cargarCrear(Model model){
        UsuarioDTO usuario = new UsuarioDTO();
        model.addAttribute("userDTO",usuario);
        return "perfilUsuario";

    }
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id") Integer id, Model model) throws ParseException {
        //UsuarioDTO usuarioDTO = (UsuarioDTO) this.usuarioService.findUsuarioEventobyId(id);
        this.usuarioService.remove(id);
        return "redirect:/inicioAdmin";
    }

    @GetMapping("/perfil")
    public String doPerfil(Model model, HttpSession session) {
        model.addAttribute("userDTO", (UsuarioDTO) session.getAttribute("userDTO"));
        return "perfilUsuario";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("userDTO") UsuarioDTO userDTO, Model model, HttpSession session) {
        UsuarioDTO userSesionDTO = (UsuarioDTO) session.getAttribute("userDTO");
        Integer idRol = this.usuarioService.getIdRolUsuario(userSesionDTO);
        Boolean admin = (idRol==1);
        String strTo = "perfilUsuario";
        if (userDTO.getContrasenya2().isEmpty() || userDTO.getContrasenya().equals(userDTO.getContrasenya2())) {
            if(admin){
                idRol = userDTO.getRolDTOByRol().getId();
            }
            this.usuarioService.guardarUsuario(userDTO, idRol);
            if (userDTO.getId() == null) { //creando
                if(admin) {
                    strTo = "redirect:/inicioAdmin";
                }
                userDTO.setContrasenya2("");
            } else { //editando
                model.addAttribute("guardado", true);
            }
            if(!admin) {
                session.setAttribute("userDTO", userDTO);
            }
        } else {
            model.addAttribute("errorLog", "Las contraseñas no coinciden");

            if (admin) { //creando o editando desde Admin

                strTo = "perfilUsuario";
                model.addAttribute("userDTO",userDTO);
            }
        }
        return strTo;
    }


}

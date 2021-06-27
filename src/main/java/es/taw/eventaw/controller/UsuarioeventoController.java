package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.service.EntradaService;
import es.taw.eventaw.service.UsuarioService;
import es.taw.eventaw.service.UsuarioeventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/usuarioEvento")
public class UsuarioeventoController {
    private UsuarioService usuarioService;
    private EntradaService entradaService;
    private UsuarioeventoService usuarioeventoService;

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setUsuarioeventoService(UsuarioeventoService usuarioeventoService) {
        this.usuarioeventoService = usuarioeventoService;
    }

    @GetMapping("/registrarFormulario")
    public String cargarFormulario(Model model) {
        UsuarioDTO ueEmpty = new UsuarioDTO();
        model.addAttribute("userDTO", ueEmpty);
        return "registroUsuario";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("userDTO") UsuarioDTO userDTO, Model model, HttpSession session) throws ParseException {
        String strTo = "perfilUsuario";
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        if (userDTO.getContrasenya2().isEmpty() || userDTO.getContrasenya().equals(userDTO.getContrasenya2())) {
            if (usuarioService.getCorreoYaRegistrado(userDTO.getCorreo()) && !userDTO.isEditar()) {
                model.addAttribute("errorLog", "Este correo ya existe. Por favor, utiliza otro correo.");

                if (userDTO.getId() == null) { //creando
                    strTo = "registroUsuario";
                } else if (usuarioDTO.getRolDTOByRol().getId() == 2) {
                    strTo = "perfilUsuarioevento";
                }

            } else {

                this.usuarioService.guardarUsuario(userDTO, 2);
                if (userDTO.getId() == null) { //creando
                    strTo = "redirect:/inicioUEvento";
                    userDTO = this.usuarioService.comprobarCredenciales(userDTO.getCorreo(), userDTO.getContrasenya());
                } else {
                    if (usuarioDTO.getRolDTOByRol().getId() == 1) {
                        strTo = "redirect:/inicioAdmin";
                    } else if (usuarioDTO.getRolDTOByRol().getId() == 2) {
                        strTo = "perfilUsuarioevento";
                    }
                    model.addAttribute("guardado", true);

                }
                session.setAttribute("userDTO", userDTO);

            }
        } else {
            model.addAttribute("errorLog", "Las contraseñas no coinciden");

            if (userDTO.getId() == null) { //creando
                strTo = "registroUsuario";
            } else if (usuarioDTO.getRolDTOByRol().getId() == 2) {
                strTo = "perfilUsuarioevento";
            }
        }
        return strTo;
    }

    @GetMapping("/misEntradas")
    public String doMisEntradas(Model model, HttpSession session) throws ParseException {
        List<EntradaDTO> entradasFuturas = this.entradaService.getEntradasFuturas((UsuarioDTO) session.getAttribute("userDTO"));
        List<EntradaDTO> entradasPasadas = this.entradaService.getEntradasPasadas((UsuarioDTO) session.getAttribute("userDTO"));
        model.addAttribute("entradasFuturas", entradasFuturas);
        model.addAttribute("entradasPasadas", entradasPasadas);
        model.addAttribute("eventoDTO", new EventoDTO());
        return "entrada";
    }

    @GetMapping("/perfil")
    public String doPerfil(Model model, HttpSession session) {
        UsuarioDTO userDTO = (UsuarioDTO) session.getAttribute("userDTO");
        userDTO.setEditar(true);
        model.addAttribute("userDTO", userDTO);
        return "perfilUsuarioevento";
    }

    @GetMapping("/editar/{id}")
    public String cargarEditar(@PathVariable("id") Integer id, Model model) throws ParseException {
        UsuarioDTO usuario = this.usuarioService.findUsuarioEventobyId(id);
        usuario.setEditar(true);
        model.addAttribute("userDTO", usuario);
        return "perfilUsuario";
    }

}

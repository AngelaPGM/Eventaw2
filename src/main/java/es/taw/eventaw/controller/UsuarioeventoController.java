package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.dto.UsuarioeventoDTO;
import es.taw.eventaw.service.EntradaService;
import es.taw.eventaw.service.UsuarioService;
import es.taw.eventaw.service.UsuarioeventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public String doGuardar(@ModelAttribute("userDTO") UsuarioDTO userDTO, Model model, HttpSession session) {
        String strTo = "perfilUsuario";
        if (userDTO.getContrasenya2().isEmpty() || userDTO.getContrasenya().equals(userDTO.getContrasenya2())) {
            if (userDTO.getId() == null) { //creando
                this.usuarioService.guardarUsuario(userDTO);
                session.setAttribute("userDTO", userDTO);
                strTo = "redirect:/inicioUEvento";
            } else {
                this.usuarioService.guardarUsuario(userDTO);
                model.addAttribute("guardado", true);
                session.setAttribute("userDTO", userDTO);
            }

        } else {
            model.addAttribute("errorLog", "Las contraseñas no coinciden");

            if (userDTO.getId() == null) { //creando
                strTo = "registroUsuario";
            }
        }
        return strTo;
    }

    @GetMapping("/misEntradas")
    public String doMisEntradas(Model model, HttpSession session) {
        List<EntradaDTO> entradasFuturas = this.entradaService.getEntradasFuturas((UsuarioDTO) session.getAttribute("userDTO"));
        List<EntradaDTO> entradasPasadas = this.entradaService.getEntradasPasadas((UsuarioDTO) session.getAttribute("userDTO"));
        model.addAttribute("entradasFuturas", entradasFuturas);
        model.addAttribute("entradasPasadas", entradasPasadas);
        return "entrada";
    }

    @GetMapping("/perfil")
    public String doPerfil(Model model, HttpSession session) {
        model.addAttribute("userDTO", (UsuarioDTO) session.getAttribute("userDTO"));
        return "perfilUsuario";
    }

}

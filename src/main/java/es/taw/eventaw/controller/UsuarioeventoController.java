package es.taw.eventaw.controller;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.RolRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dao.UsuarioeventoRepository;
import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.dto.UsuarioeventoDTO;
import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.Rol;
import es.taw.eventaw.entity.Usuario;
import es.taw.eventaw.entity.Usuarioevento;
import es.taw.eventaw.service.EntradaService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/usuarioEvento")
public class UsuarioeventoController {
    private UsuarioService usuarioService;
    private EntradaService entradaService;

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registrarFormulario")
    public String cargarFormulario(Model model) {
        UsuarioeventoDTO ueEmpty = new UsuarioeventoDTO();
        model.addAttribute("usuarioEventoDTO", ueEmpty);
        List<String> sexos = new ArrayList<>();
        sexos.add("H");
        sexos.add("M");
        model.addAttribute("sexos", sexos);
        return "registroUsuario";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("usuarioDTO") UsuarioeventoDTO inputData, Model model, HttpSession session) {
        if(inputData.getUsuarioDTO().getContrasenya().equals(inputData.getUsuarioDTO().getContrasenya2())){
            UsuarioDTO userDTO = this.usuarioService.nuevoUsuario(inputData);
            session.setAttribute("userDTO", userDTO);

            return "redirect:/inicioUEvento";
        } else {
            model.addAttribute("errorLog", "Las contraseñas no coinciden");
            return "registroUsuario";
        }
    }

    @GetMapping("/misEntradas")
    public String doMisEntradas(Model model, HttpSession session){
        List<EntradaDTO> entradasFuturas = this.entradaService.getEntradasFuturas((UsuarioDTO)session.getAttribute("userDTO"));
        List<EntradaDTO> entradasPasadas = this.entradaService.getEntradasPasadas((UsuarioDTO)session.getAttribute("userDTO"));
        model.addAttribute("entradasFuturas", entradasFuturas);
        model.addAttribute("entradasPasadas", entradasPasadas);
        return "entrada";
    }
}

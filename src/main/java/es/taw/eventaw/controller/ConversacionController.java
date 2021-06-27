package es.taw.eventaw.controller;

import es.taw.eventaw.dto.ConversacionDTO;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.MensajeDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Conversacion;
import es.taw.eventaw.entity.Mensaje;
import es.taw.eventaw.service.ConversacionService;
import es.taw.eventaw.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/conversacion")
public class ConversacionController {
    ConversacionService conversacionService;
    MensajeService mensajeService;

    @Autowired
    public void setConversacionService(ConversacionService conversacionService) {
        this.conversacionService = conversacionService;
    }

    @Autowired
    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("/iniciar")
    public String iniciar(Model model, HttpSession session) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        ConversacionDTO conversacionDTO = this.conversacionService.crearConversacion(usuarioDTO.getId());
        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setConversacion(conversacionDTO);
        mensajeDTO.setEmisor(usuarioDTO);
        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("mensaje", mensajeDTO);
        model.addAttribute("conversacionDTO", conversacionDTO);
        model.addAttribute("error", "");
        return "Conversacion";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable("id") Integer id,Model model, HttpSession session) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        ConversacionDTO conversacionDTO = this.conversacionService.getConversacionById(id);
        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setConversacion(conversacionDTO);
        mensajeDTO.setEmisor(usuarioDTO);
        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("mensaje", mensajeDTO);
        model.addAttribute("conversacionDTO", conversacionDTO);
        model.addAttribute("error", "");
        return "Conversacion";
    }

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(@ModelAttribute("mensaje") MensajeDTO mensajeDTO, Model model, HttpSession session) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        MensajeDTO mensaje = this.mensajeService.guardar(mensajeDTO);
        ConversacionDTO conversacionDTO = this.conversacionService.getConversacionById(mensajeDTO.getConversacion().getId());
        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("conversacionDTO", conversacionDTO);
        model.addAttribute("error", "");
        return "redirect:/conversacion/ver/" + conversacionDTO.getId();
    }

    @PostMapping("/filtrar")
    public String filtrar(@ModelAttribute("filtro") ConversacionDTO conversacionDTO, Model model, HttpSession session) throws ParseException {
        String correo = conversacionDTO.getUsuarioConversacion().getCorreo();
        String strTo = "";
        if(correo == null){
            strTo = "redirect:/conversacion/verChats";
        }else{
            UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
            List<ConversacionDTO> chats = this.conversacionService.getChatsByCorreo(conversacionDTO.getUsuarioConversacion().getCorreo());
            ConversacionDTO c = new ConversacionDTO();
            model.addAttribute("chats", chats);
            model.addAttribute("filtro", conversacionDTO);
            model.addAttribute("error", "");
            strTo = "teleoperador";
        }
        return strTo;
    }

    @GetMapping("/borrar/{id}")
    public String doBorrar (@PathVariable("id") Integer id) throws ParseException {
        this.conversacionService.borrar(id);
        return "redirect:/conversacion/verChats";
    }

    @GetMapping("/verChats")
    public String verChats (Model model, HttpSession session) throws ParseException {
        ConversacionDTO c = new ConversacionDTO();
        model.addAttribute("filtro", c);
        model.addAttribute("chats", this.conversacionService.getChatsDto());
        model.addAttribute("userDto",session.getAttribute("userDTO"));
        return "teleoperador";
    }
}

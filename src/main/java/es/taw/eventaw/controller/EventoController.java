package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/evento")
public class EventoController {
    private EventoService eventoService;

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/filtrar")
    public String doFiltrarEventos(@ModelAttribute("eventoDTO") EventoDTO inputData, Model model) throws ParseException {
        List<EventoDTO> filtrados = this.eventoService.filtradoNombre(inputData.getTitulo(),
                new SimpleDateFormat().parse(inputData.getFecha()),
                new SimpleDateFormat().parse(inputData.getFechacompra()));
        model.addAttribute("eventosFuturos",filtrados);
        return "inicioUEvento";
    }

    @GetMapping("/crear")
    public String cargarCrear(Model model){
        model.addAttribute("eventoDTO", new EventoDTO());
        model.addAttribute("error", "");
        return "formularioEvento";
    }

    @GetMapping("/editar/{id}")
    public String cargarEditar(@PathVariable Integer id, Model model){
        model.addAttribute("eventoDTO", this.eventoService.findEventobyId(id));
        model.addAttribute("error", "");
        return "formularioEvento";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrar(@PathVariable Integer id){
        this.eventoService.remove(id);
        return "redirect:/inicioCreador";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("eventoDTO") EventoDTO eventoDTO, HttpSession session) throws ParseException {
        this.eventoService.save(eventoDTO, (UsuarioDTO) session.getAttribute("userDTO"));
        return "redirect:/inicioCreador";
    }
}

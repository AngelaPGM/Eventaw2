package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String doFiltrarEventos(@ModelAttribute("eventoDTO") EventoDTO inputData, Model model){
        List<EventoDTO> filtrados = this.eventoService.filtradoNombre(inputData.getTitulo(), inputData.getFecha(), inputData.getFechacompra());
        model.addAttribute("eventosFuturos",filtrados);
        return "inicioUEvento";
    }

    @GetMapping("/crear")
    public String cargarCrear(Model model){
        model.addAttribute("evento", new EventoDTO());
        model.addAttribute("error", "");
        return "formularioEvento";
    }

    @GetMapping("/editar/{id}")
    public String cargarEditar(@PathVariable Integer id, Model model){
        model.addAttribute("evento", this.eventoService.findEventobyId(id));
        model.addAttribute("error", "");
        return "formularioEvento";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrar(@PathVariable Integer id){
        this.eventoService.remove(id);
        return "redirect:/inicioCreador";
    }
}

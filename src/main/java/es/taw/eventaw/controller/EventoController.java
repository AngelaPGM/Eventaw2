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
        List<EventoDTO> filtrados = this.eventoService.filtrado(inputData.getTitulo(), inputData.getFecha(), inputData.getFechacompra());
        model.addAttribute("eventosFuturos",filtrados);
        model.addAttribute("eventoDTO", inputData);
        return "inicioUEvento";
    }

    @GetMapping("/crear")
    public String cargarCrear(Model model) throws ParseException {
        model.addAttribute("eventoDTO", new EventoDTO());
        model.addAttribute("error", "");
        return "formularioEvento";
    }

    @GetMapping("/editar/{id}")
    public String cargarEditar(@PathVariable Integer id, Model model) throws ParseException {
        EventoDTO eventoDTO = this.eventoService.findEventobyId(id);
        model.addAttribute("eventoDTO", eventoDTO);
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

    @GetMapping("/comprarEntradas/{eventoId}")
    public String cargarComprarEntradas(@PathVariable Integer eventoId, Model model, HttpSession session) throws ParseException {
        model.addAttribute("eventoDTO", this.eventoService.findEventobyId(eventoId));
        model.addAttribute("entradas", this.eventoService.getEntradasPuedeComprar(eventoId, (UsuarioDTO) session.getAttribute("userDTO")));
        return "ventaEntradas";
    }

    @PostMapping("/aceptarPago")
    public String cargarAceptarPago(@ModelAttribute("eventoDTO") EventoDTO eventoDTO, Model model, HttpSession session) throws ParseException {
        if(eventoDTO.getNumfilas() != null){
            model.addAttribute("evento", this.eventoService.findEventobyId(eventoDTO.getId()));
            model.addAttribute("numEntradas", eventoDTO.getMaxentradasusuario());
            return "confirmarPago";
        } else {
            UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
            return this.doConfirmarPago(usuarioDTO, this.eventoService.findEventobyId(eventoDTO.getId()), eventoDTO.getMaxentradasusuario());
        }
    }

    private String doConfirmarPago(UsuarioDTO usuarioDTO, EventoDTO eventoDTO, Integer numEntradas) {
        this.eventoService.tramitarEntradas(usuarioDTO, eventoDTO, numEntradas);
        return "redirect:/inicioUEvento";
    }
}

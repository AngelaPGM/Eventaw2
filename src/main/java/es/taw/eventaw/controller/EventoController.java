package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.service.EtiquetaService;
import es.taw.eventaw.service.EventoService;
import es.taw.eventaw.service.UsuarioService;
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
    private UsuarioService usuarioService;
    private EtiquetaService etiquetaService;

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) { this.usuarioService = usuarioService; }

    @Autowired
    public void setEtiquetaService(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @PostMapping("/filtrarUEvento")
    public String doFiltrarEventos(@ModelAttribute("eventoDTO") EventoDTO inputData, Model model, HttpSession session) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        List<EventoDTO> filtrados = this.eventoService.filtrado(inputData.getTitulo(), inputData.getFecha(), inputData.getFechacompra());
        model.addAttribute("eventosFuturos",filtrados);
        model.addAttribute("eventoDTO", inputData);
        if(usuarioDTO.getRolDTOByRol().getId() == 1 ){ //Si soy Administrador.
            model.addAttribute("usuarios",this.usuarioService.findAll());
            model.addAttribute("usuarioFiltroDTO", new UsuarioDTO());
            return "inicioAdministrador";
        }
        return "inicioUEvento";
    }

    @PostMapping("filtrarCreador")
    public String doFiltrarEventosCreador(@ModelAttribute("eventoDTO") EventoDTO eventoDTO, Model model, HttpSession session) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        List<EventoDTO> filtrados = this.eventoService.filtradoCreador(usuarioDTO.getId(), eventoDTO.getTitulo(), eventoDTO.getFecha(), eventoDTO.getFechacompra());
        model.addAttribute("misEventos", filtrados);
        model.addAttribute("todosEventos", this.eventoService.findAll());
        return "inicioCreador";
    }

    @GetMapping("/crear")
    public String cargarCrear(Model model) throws ParseException {
        model.addAttribute("eventoDTO", new EventoDTO());
        model.addAttribute("error", "");
        model.addAttribute("todasEtiquetas", this.etiquetaService.findAll());
        return "formularioEvento";
    }

    @GetMapping("/editar/{id}")
    public String cargarEditar(@PathVariable Integer id, Model model) throws ParseException {
        EventoDTO eventoDTO = this.eventoService.findEventobyId(id);
        model.addAttribute("eventoDTO", eventoDTO);
        model.addAttribute("error", "");
        model.addAttribute("todasEtiquetas", this.etiquetaService.findAll());
        model.addAttribute("todasEtiquetasString", this.etiquetaService.findAllString());
        return "formularioEvento";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrar(@PathVariable Integer id, HttpSession session){
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        this.eventoService.remove(id);

        if(usuarioDTO.getRolDTOByRol().getId() !=1 ) {
            return "redirect:/inicioCreador";
        }else{
            return "redirect:/inicioAdmin";
        }
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("eventoDTO") EventoDTO eventoDTO, HttpSession session) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        this.eventoService.save(eventoDTO, usuarioDTO);
        if(usuarioDTO.getRolDTOByRol().getId() !=1 ) {
            return "redirect:/inicioCreador";
        }else {
            return "redirect:/inicioAdmin";
        }
    }

    @GetMapping("/comprarEntradas/{eventoId}")
    public String cargarComprarEntradas(@PathVariable Integer eventoId, Model model, HttpSession session) throws ParseException {
        model.addAttribute("eventoDTO", this.eventoService.findEventobyId(eventoId));
        model.addAttribute("entradas", this.eventoService.getEntradasPuedeComprar(eventoId, (UsuarioDTO) session.getAttribute("userDTO")));
        return "ventaEntradas";
    }

    @PostMapping("/aceptarPago/")
    public String cargarAceptarPago(@ModelAttribute("eventoDTO") EventoDTO eventoDTO, Model model, HttpSession session) throws ParseException {
        if(eventoDTO.getNumfilas() != null){
            model.addAttribute("evento", this.eventoService.findEventobyId(eventoDTO.getId()));
            model.addAttribute("numEntradas", new Double(eventoDTO.getMaxentradasusuario()));
            String error = eventoDTO.getTitulo();
            if(error == null) error = "";
            model.addAttribute("error", error);
            model.addAttribute("asientos", this.eventoService.getAsientos(eventoDTO.getId()));
            return "confirmarPago";
        } else {
            return this.doConfirmarPago(eventoDTO, eventoDTO.getMaxentradasusuario(), session, model);
        }
    }

    @PostMapping("/confirmarPago")
    private String doConfirmarPago(@ModelAttribute("eventoDTO") EventoDTO eventoDTO, Integer numEntradas, HttpSession session, Model model) throws ParseException {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
        String error = this.eventoService.tramitarEntradas(usuarioDTO, eventoDTO, numEntradas);
        if(error.equals("")){
            return "redirect:/inicioUEvento";
        } else {
            eventoDTO.setTitulo(error);
            return this.cargarAceptarPago(eventoDTO, model, session);
        }
    }
}

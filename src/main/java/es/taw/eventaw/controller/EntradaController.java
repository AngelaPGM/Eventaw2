package es.taw.eventaw.controller;

import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.dto.EventoDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/entrada")
public class EntradaController {
    EntradaService entradaService;

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("eventoDTO")EventoDTO eventoDTO, Model model, HttpSession session) throws ParseException {
        List<EntradaDTO> entradasFuturas = this.entradaService.filtrado(eventoDTO, (UsuarioDTO)session.getAttribute("userDTO"));
        model.addAttribute("entradasFuturas", entradasFuturas);
        List<EntradaDTO> entradasPasadas = this.entradaService.getEntradasPasadas((UsuarioDTO) session.getAttribute("userDTO"));
        model.addAttribute("entradasPasadas", entradasPasadas);
        model.addAttribute("eventoDTO", eventoDTO);
        return "entrada";
    }
}

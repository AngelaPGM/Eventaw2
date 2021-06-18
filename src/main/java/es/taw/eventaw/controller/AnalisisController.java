package es.taw.eventaw.controller;

import es.taw.eventaw.dto.AnalisisDTO;
import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.service.AnalisisService;
import es.taw.eventaw.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/analisis")
public class AnalisisController {

    private AnalisisService analisisService;
    private EntradaService entradaService;

    @Autowired
    public void setAnalisisService(AnalisisService analisisService) {
        this.analisisService = analisisService;
    }

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping("/")
    public String listarAnalisis (Model model, HttpSession session) {
        List<AnalisisDTO> lista = this.analisisService.listarAnalisis();
        model.addAttribute("listaAnalisis", lista);
        return "analista";
    }

    @GetMapping("/crear")
    public String doCrear(Model model){
        AnalisisDTO a = new AnalisisDTO();
        model.addAttribute("a", a);
        return "analisis";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("a") AnalisisDTO a){
        this.analisisService.doGuardar(a);
        return "analisis";
    }

    @GetMapping("/ver/{id}")
    public String doResultado(@PathVariable("id") Integer id, Model model){
        AnalisisDTO analisis = this.analisisService.findById(id);
        model.addAttribute("analisis", analisis);

        List<EntradaDTO> listaEntradas = this.entradaService.findByAnalisis(analisis);
        model.addAttribute("listaEntradas", listaEntradas);

        return "analisis";
    }

    /*
    @Autowired
    public void setAnalisisRepository(AnalisisRepository analisisRepository) {
        this.analisisRepository = analisisRepository;
    }

    @Autowired
    public void setEntradaRepository(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    @GetMapping("/analisis")
    public String doListar(Model model){
        List<Analisis> listaAnalisis = this.analisisRepository.findAll();
        model.addAttribute("listaAnalisis", listaAnalisis);
        return "analista";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("a") Analisis a){
        a.setId(0);
        this.analisisRepository.save(a);
        return "resultados";
    }

    @GetMapping("/ver/{id}")
    public String doResultado(@PathVariable("id") Integer id, Model model){
        Optional<Analisis> analisisOptional = this.analisisRepository.findById(id);
        if(analisisOptional.isPresent()){
            Analisis analisis = analisisOptional.get();
            model.addAttribute("analisis", analisis);
            List<Entrada> listaEntradas = this.entradaRepository.findEntradasByAnalisis(analisis);
            model.addAttribute("listaEntradas", listaEntradas);
        }
        return "resultados";
    }

    @GetMapping("/editar/{id}")
    public String doEditar(@PathVariable("id") Integer id){
        return "analisis";
    }
    */
}

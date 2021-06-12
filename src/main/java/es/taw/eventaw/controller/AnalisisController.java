package es.taw.eventaw.controller;

import es.taw.eventaw.dao.AnalisisRepository;
import es.taw.eventaw.dao.EntradaRepository;
import es.taw.eventaw.entity.Analisis;
import es.taw.eventaw.entity.Entrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/analisis")
public class AnalisisController {

    private AnalisisRepository analisisRepository;
    private EntradaRepository entradaRepository;

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

    @GetMapping("/crear")
    public String doCrear(Model model){
        Analisis a = new Analisis();
        model.addAttribute("a", a);
        return "analisis";
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
}

package es.taw.eventaw.controller;

import es.taw.eventaw.dto.AnalisisDTO;
import es.taw.eventaw.dto.EntradaDTO;
import es.taw.eventaw.service.AnalisisService;
import es.taw.eventaw.service.EntradaService;
import es.taw.eventaw.vo.FiltroAnalisis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // change the format according to your need.
        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/")
    public String listarAnalisis (Model model) {
        FiltroAnalisis filtro = new FiltroAnalisis();
        return doFiltrar(filtro, model);
    }

    @PostMapping("/filtrar")
    public String doFiltrar (@ModelAttribute("filtro") FiltroAnalisis filtro, Model model) {
        List<AnalisisDTO> lista = null;
        if(filtro.getNombre().isEmpty() || filtro.getNombre() == null){
            lista = this.analisisService.listarAnalisis();
        }else {
            lista = this.analisisService.filtrarAnalisis(filtro.getNombre());
        }
        model.addAttribute("filtro", filtro);
        model.addAttribute("listaAnalisis", lista);

        return "analista";
    }

    //Deprecated
    @GetMapping("/crear")
    public String doCrear(Model model) throws ParseException {
        AnalisisDTO analisis = new AnalisisDTO();
        model.addAttribute("analisis", analisis);

        List<EntradaDTO> listaEntradas = this.entradaService.findAll();
        model.addAttribute("listaEntradas", listaEntradas);

        model.addAttribute("totales", this.entradaService.numeroTotal());
        return "analisis";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("analisis") AnalisisDTO a){
        //Esto se debe hacer en service
        if(a.getSexo().equals("N")){
            a.setSexo(null);
        }
        Integer id = this.analisisService.doGuardar(a);

        return "redirect:/analisis/ver/"+id;
    }

    //ver + crear
    @GetMapping("/ver/{id}")
    public String doResultado(@PathVariable("id") Integer id, Model model) throws ParseException {

        AnalisisDTO analisis;
        if(id == -1){
            analisis = new AnalisisDTO();
        }else {
            analisis = this.analisisService.findById(id);
        }
        model.addAttribute("analisis", analisis);

        List<EntradaDTO> listaEntradas = this.entradaService.findByAnalisis(analisis);
        model.addAttribute("listaEntradas", listaEntradas);

        model.addAttribute("totales", this.entradaService.numeroTotal());

        //HOMBRES,MUJERES
        model.addAttribute("estadistico", this.entradaService.estadistico(listaEntradas));

        return "analisis";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrar(@PathVariable("id") Integer id){
        this.analisisService.doBorrar(id);
        return "redirect:/analisis/";
    }

    @GetMapping("/copiar/{id}")
    public String doCopiar(@PathVariable("id") Integer id, Model model) throws ParseException {
        AnalisisDTO copia = this.analisisService.doCopiar(id);

        model.addAttribute("analisis", copia);

        List<EntradaDTO> listaEntradas = this.entradaService.findByAnalisis(copia);
        model.addAttribute("listaEntradas", listaEntradas);

        model.addAttribute("totales", this.entradaService.numeroTotal());

        return "analisis";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session, Model model) {
        session.removeAttribute("userDTO");
        return "redirect:/";
    }
}

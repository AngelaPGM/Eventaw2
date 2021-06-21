package es.taw.eventaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.PostRemove;

@Controller
@RequestMapping("/evento")
public class EventoController {

    @PostMapping("/filtrar")
    public String doFiltrarEventos(){

        return "inicioUEvento";
    }
}

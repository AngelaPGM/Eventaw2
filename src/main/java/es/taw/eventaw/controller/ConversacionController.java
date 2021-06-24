package es.taw.eventaw.controller;

import es.taw.eventaw.dto.ConversacionDTO;
import es.taw.eventaw.dto.MensajeDTO;
import es.taw.eventaw.dto.UsuarioDTO;
import es.taw.eventaw.entity.Conversacion;
import es.taw.eventaw.service.ConversacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/conversacion")
public class ConversacionController {
    ConversacionService conversacionService;

    @Autowired
    public void setConversacionService(ConversacionService conversacionService) {
        this.conversacionService = conversacionService;
    }



}

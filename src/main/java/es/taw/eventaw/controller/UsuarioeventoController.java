package es.taw.eventaw.controller;

import es.taw.eventaw.dao.EventoRepository;
import es.taw.eventaw.dao.RolRepository;
import es.taw.eventaw.dao.UsuarioRepository;
import es.taw.eventaw.dao.UsuarioeventoRepository;
import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.Rol;
import es.taw.eventaw.entity.Usuario;
import es.taw.eventaw.entity.Usuarioevento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UsuarioeventoController {
    @Autowired
    private UsuarioeventoRepository usuarioeventoRepository;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setUsuarioeventoRepository(UsuarioeventoRepository usuarioeventoRepository) {
        this.usuarioeventoRepository = usuarioeventoRepository;
    }

    @GetMapping("/usuarioEvento/registrarFormulario")
    public String cargarFormulario(Model model) {
        Usuarioevento ueEmpty = new Usuarioevento();
        model.addAttribute("usuario", ueEmpty);
        List<String> sexos = new ArrayList<>();
        sexos.add("H");
        sexos.add("M");
        model.addAttribute("sexos", sexos);
        return "registroUsuario";
    }

    @PostMapping("/usuarioEvento/guardar")
    public String doGuardar(@ModelAttribute("usuario") Usuarioevento inputData, Model model) {
        Usuario usuarioNuevo = new Usuario();

        Rol rol = this.rolRepository.getById(2);//Si borro esto me da una violacion de campo NotNull
        usuarioNuevo.setRolByRol(rol);
        usuarioNuevo.setCorreo(inputData.getUsuarioByIdusuario().getCorreo());
        usuarioNuevo.setContrasenya(inputData.getUsuarioByIdusuario().getContrasenya());
        List<Usuarioevento> aux = new ArrayList<>();
        aux.add(inputData);
        usuarioNuevo.setUsuarioeventosById(aux);//Esto es raro porque parece que permite a un usuario tener varios UsuarioEventos (si no paso una lista peta)
        inputData.setUsuarioByIdusuario(usuarioNuevo);
        this.usuarioRepository.save(usuarioNuevo);
        this.usuarioeventoRepository.save(inputData);

        List<Evento> eventosFuturos = this.eventoRepository.findEventoByFechaAfter(new Date());
        model.addAttribute("eventosFuturos", eventosFuturos);

        return "inicioUEvento";
    }
}

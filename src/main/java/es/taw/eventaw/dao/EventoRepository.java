package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findEventoByFechaAfter(Date fecha);

    List<Evento> findEventoByTitulo(String titulo);

    List<Evento> findEventoByTituloAndFechaAfterAndFechaBefore(String titulo, Date fechaIni, Date fechaFin);

    List<Evento> findEventoByTituloAndFechaAfter(String titulo, Date fechaIni);

    List<Evento> findEventoByTituloAndFechaBefore(String titulo, Date fechaFin);

    List<Evento> findEventoByFechaAfterAndFechaBefore(Date fechaIni, Date fechaFin);

    List<Evento> findEventoByFechaBefore(Date fechaFin);
}

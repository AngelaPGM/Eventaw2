package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findEventoByFechaAfter(Date fecha);
}

package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findEventoByFechaAfter(Date fecha);

    List<Evento> findEventoByTitulo(String titulo);

    @Query("select e from Evento e where lower(e.titulo) like concat('%', lower(:titulo), '%') and e.fecha >= :fechaIni and  e.fechacompra < :fechaFin")
    List<Evento> findEventoByTituloAndFechaAfterAndFechaBefore(String titulo, Date fechaIni, Date fechaFin);

    List<Evento> findEventoByFechaAfterAndFechaBefore(Date fechaIni, Date fechaFin);

    @Query("select e from Evento e where e.usuarioByCreador.id = :id and lower(e.titulo) like concat('%', lower(:titulo), '%') and e.fecha >= :fechaIni and  e.fechacompra < :fechaFin")
    List<Evento> findEventoByCreadorAndTituloAndFechaAfterAndFechaBefor(Integer id, String titulo, Date fechaIni, Date fechaFin);

    @Query("select e from  Evento e where e.usuarioByCreador.id = :id and e.fecha >= :fechaIni and  e.fechacompra < :fechaFin")
    List<Evento> findEventoByCreadorAndFechaAfterAndFechaBefore(Integer id, Date fechaIni, Date fechaFin);
}

package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    @Query("select e from Entrada e where e.eventoByEvento.fecha >= :mayor")
    public List<Entrada> findEntradasFechaMayor(@Param("mayor") Date mayor);

    @Query("select e from Entrada e where e.eventoByEvento.fecha <= :menor")
    public List<Entrada> findEntradasFechaMenor(@Param("menor") Date menor);

    @Query("select e from Entrada e where e.eventoByEvento.precio >= :mayor")
    public List<Entrada> findEntradasPrecioMayor(@Param("mayor") Double mayor);

    @Query("select e from Entrada e where e.eventoByEvento.precio <= :menor")
    public List<Entrada> findEntradasPrecioMenor(@Param("menor") Double menor);

    @Query("select e from Entrada e where e.usuarioeventoByUsuario.fechanacimiento >= :mayor")
    public List<Entrada> findEntradasEdadMayor(@Param("mayor") Date mayor);

    @Query("select e from Entrada e where e.usuarioeventoByUsuario.fechanacimiento <= :menor")
    public List<Entrada> findEntradasEdadMenor(@Param("menor") Date menor);

    @Query("select e from Entrada e where e.usuarioeventoByUsuario.sexo like :sexo")
    public List<Entrada> findEntradasSexo(@Param("sexo") String sexo);

    @Query("select e from Entrada e where e.usuarioeventoByUsuario.id = :id and e.eventoByEvento.fecha >= :date")
    public List<Entrada> findEntradaByUsuarioeventoAndEventoByEventoAfter(Integer id, Date date);

    @Query("select e from Entrada e where e.usuarioeventoByUsuario.id = :id and e.eventoByEvento.fecha < :date")
    public List<Entrada> findEntradaByUsuarioeventoAndEventoByEventoBefore(Integer id, Date date);

    @Query("select e from Entrada e where e.usuarioeventoByUsuario.id = :id and lower(e.eventoByEvento.titulo) like concat('%', lower(:titulo), '%') and e.eventoByEvento.fecha >= :fechaIni and e.eventoByEvento.fecha <= :fechaFin")
    public List<Entrada> findEntradaByEvento(Integer id, String titulo, Date fechaIni, Date fechaFin);

    @Query("select e from Entrada e where e.usuarioeventoByUsuario.id = :id and e.eventoByEvento.fecha >= :fechaIni and e.eventoByEvento.fecha <= :fechaFin")
    public List<Entrada> findEntradaByEventoSoloFechas(Integer id, Date fechaIni, Date fechaFin);

    @Query("select count(e) from Entrada e")
    public Integer numeroTotal();
}

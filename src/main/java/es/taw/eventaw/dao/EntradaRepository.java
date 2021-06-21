package es.taw.eventaw.dao;

import es.taw.eventaw.dto.AnalisisDTO;
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

    @Query("select e from Entrada e where e.eventoByEvento.fecha <= :menor")
    public List<Entrada> findEntradasPrecioMenor(@Param("menor") Double menor);

    public List<Entrada> findEntradaByUsuarioeventoByUsuarioAndEventoByEventoAfter(Integer id, java.util.Date date);

    public List<Entrada> findEntradaByUsuarioeventoByUsuarioAndEventoByEventoBefore(Integer id, java.util.Date date);
}

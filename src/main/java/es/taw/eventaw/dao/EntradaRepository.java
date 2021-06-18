package es.taw.eventaw.dao;

import es.taw.eventaw.dto.AnalisisDTO;
import es.taw.eventaw.entity.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    @Query("select e from Entrada e where e.eventoByEvento.fecha >= :analisis")
    public List<Entrada> findEntradasByAnalisis(@Param("analisis") AnalisisDTO analisisDTO);
}

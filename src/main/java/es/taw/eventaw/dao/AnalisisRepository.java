package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalisisRepository extends JpaRepository<Analisis, Integer> {

    @Query("select a from Analisis a where a.nombre like concat('%', :nombre, '%')")
    public List<Analisis> filtrarByNombre(@Param("nombre") String filtro);
}

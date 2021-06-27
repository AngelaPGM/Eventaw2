package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {

    public Etiqueta findEtiquetaByNombre(String nombre);
}

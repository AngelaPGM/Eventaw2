package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje,Integer> {
}

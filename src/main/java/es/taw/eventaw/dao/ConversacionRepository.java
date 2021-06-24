package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<Conversacion,Integer> {
}

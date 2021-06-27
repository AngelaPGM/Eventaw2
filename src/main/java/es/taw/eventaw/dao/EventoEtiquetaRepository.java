package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Evento;
import es.taw.eventaw.entity.EventoEtiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EventoEtiquetaRepository extends JpaRepository<EventoEtiqueta, Integer> {
    @Transactional
    void deleteByEventoByEvento(Evento eventoByEvento);
}

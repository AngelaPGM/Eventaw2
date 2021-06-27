package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Conversacion;
import es.taw.eventaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository extends JpaRepository<Conversacion,Integer> {

    @Query("SELECT c FROM Conversacion c WHERE lower(c.usuarioByUsuario.correo) LIKE CONCAT('%', lower(:correo) , '%')")
    public List<Conversacion> findByCorreo (@Param("correo") String correo);
}

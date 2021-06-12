package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.correo LIKE :correo")
    Usuario findByCorreo(String correo);
}

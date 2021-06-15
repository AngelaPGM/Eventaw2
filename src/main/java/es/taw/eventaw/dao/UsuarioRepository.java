package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findUsuarioByCorreoAndContrasenya(String correo, String contrasenya);
}

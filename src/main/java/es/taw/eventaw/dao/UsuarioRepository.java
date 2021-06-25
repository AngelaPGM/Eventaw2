package es.taw.eventaw.dao;

import es.taw.eventaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findUsuarioByCorreoAndContrasenya(String correo, String contrasenya);

    Usuario findUsuarioById(Integer id);

    @Query("SELECT u FROM Usuario u WHERE lower(u.correo) LIKE CONCAT('%', lower(:correo) , '%')")
    public List<Usuario> findBySimilarCorreo (@Param("correo") String correo);

    @Query("SELECT u FROM Usuario u WHERE u.rolByRol.id = :idRol")
    public List<Usuario> findByRol (@Param("idRol") Integer idRol);

    Usuario findUsuarioByCorreo(String correo);
}

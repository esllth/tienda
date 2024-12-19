package SC403_tienda.dao;

import SC403_tienda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository; ///libreria para importar metodos crud
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    Usuario findByUsernameAndPassword(String username, String Password);

    Usuario findByUsernameOrCorreo(String username, String correo);

    boolean existsByUsernameOrCorreo(String username, String correo);
}

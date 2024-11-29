package SC403_tienda.dao;

import SC403_tienda.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<Rol, Long>{
    
}

package SC403_tienda.dao;

import SC403_tienda.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository; ///libreria para importar metodos crud


public interface ProductoDao extends JpaRepository<Producto, Long> {

}


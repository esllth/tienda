package SC403_tienda.dao;

import SC403_tienda.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository<Venta, Long>{
    
}
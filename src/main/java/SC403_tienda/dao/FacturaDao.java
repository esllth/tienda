package SC403_tienda.dao;

import SC403_tienda.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository<Factura, Long>{
    
}
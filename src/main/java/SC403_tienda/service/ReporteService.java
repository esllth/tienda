package SC403_tienda.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReporteService {
    ///Para obtener recursos descargables
    public ResponseEntity<Resource>
            generaReporte(String reporte, Map<String, Object> parametros, String tipo)
            throws IOException;
}
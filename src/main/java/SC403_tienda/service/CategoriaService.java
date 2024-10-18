package SC403_tienda.service;

import SC403_tienda.domain.Categoria;
import java.util.List;

public interface CategoriaService {

    // Se obtiene un listado de categorias en un List
    public List<Categoria> getCategorias(boolean activos);

}

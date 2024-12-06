package SC403_tienda.service;

import SC403_tienda.domain.Categoria;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public interface CategoriaService {

    ///Se obtiene un listado de categorias en un List
    public List<Categoria> getCategorias(boolean activos);

    ///Se obtiene un Categoria, a partir del id de un Cateogira
    public Categoria getCategoria(Categoria categoria);

    ///Se inserta un nuevo Categoria si el id del Categoria esta vacio
    ///Se actualiza un Categoria si el id del Categoria no esta vacio
    public void save(Categoria categoria);

    ///Se elimina e categoria que tiene el id pasado por parametro
    public void delete(Categoria categoria);

}

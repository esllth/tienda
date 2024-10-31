package SC403_tienda.dao;

import SC403_tienda.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository; ///libreria para importar metodos crud


public interface CategoriaDao extends JpaRepository<Categoria, Long> {

}


//public interface CategoriaDao extends JpaRepository <Categoria,Long> {
//
//    @Override
//    public default <S extends Categoria> S save(S entity) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//    
//    ///ya tiene metodos CRUD(create, read, update, delete)
//    ///save() -> guardar
//    ///findById() -> buscar info por un id
//    ///findAll() -> todas las categorias
//    ///deleteById() -> eliminar por id de su categoria
//    
//}
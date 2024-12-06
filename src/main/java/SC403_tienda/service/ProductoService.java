package SC403_tienda.service;

import SC403_tienda.domain.Producto;
import java.util.List;


public interface ProductoService {

    ///Se obtiene un listado de productos en un List
    public List<Producto> getProductos(boolean activos);

    ///Se obtiene un Producto, a partir del id de un Cateogira
    public Producto getProducto(Producto producto);

    ///Se inserta un nuevo Producto si el id del Producto esta vacio
    ///Se actualiza un Producto si el id del Producto no esta vacio
    public void save(Producto producto);

    ///Se elimina e producto que tiene el id pasado por parametro
    public void delete(Producto producto);

    //query 1 de consultadas ampliadas (sencillas)
     public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup); 
     
     //query 2
     public List<Producto> metodoJPQL(double precioInf, double precioSup);
     
     //query 3
     public List<Producto> metodoNativo(double precioInf, double precioSup);
             
             
}

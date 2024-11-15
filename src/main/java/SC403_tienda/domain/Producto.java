package SC403_tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data ///clase de datos, primero se declara para cada una
@Entity /// relacion con entidad
@Table(name = "producto") /// especificar con tabla relacionada en base de datos
/// implments serializable permite guardar y transmitir datos de los objetos
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L; ///para evitar problemas de compatibilidad entre objetos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///valor para identificar llave
    @Column(name = "id_producto")
    private Long idProducto; ///en la tabla tenemos un primary key =idProducto
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
//    private Long idCategoria; // relacion con otra tabla //se reemplaza con el tag de @ManyToOne
    
    //asociacion con tabla
    @ManyToOne
    @JoinColumn(name="id_categoria")
    Categoria categoria;

     public Producto() {
    }

    public Producto(String producto, boolean activo) {
        this.descripcion = producto;
        this.activo = activo;
    }
}


package SC403_tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data ///clase de datos, primero se declara para cada una
@Entity /// relacion con entidad
@Table(name = "categoria") /// especificar con tabla relacionada en base de datos
/// implments serializable permite guardar y transmitir datos de los objetos
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L; ///para evitar problemas de compatibilidad entre objetos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///valor para identificar llave
    @Column(name = "id_categoria")
    private Long idCategoria; ///en la tabla tenemos un primary key =idCategoria
    private String descripcion;
    private String rutaImagen;
    private boolean activo;

    public Categoria() {
    }

    public Categoria(String categoria, boolean activo) {
        this.descripcion = categoria;
        this.activo = activo;
    }
}

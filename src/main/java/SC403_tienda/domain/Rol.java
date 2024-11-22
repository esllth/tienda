package SC403_tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data ///clase de datos, primero se declara para cada una
@Entity /// relacion con entidad
@Table(name = "rol") /// especificar con tabla relacionada en base de datos
/// implments serializable permite guardar y transmitir datos de los objetos

public class Rol implements Serializable {

    private static final long serialVersionUID = 1L; ///para evitar problemas de compatibilidad entre objetos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///valor para identificar llave
    @Column(name = "id_rol")
    private Long idRol; ///en la tabla tenemos un primary key =idRol
    private String nombre;
    @Column(name = "id_usuario") //asociacion con tabla
    private Long idUsuario; //asociacion con tabla

}

package SC403_tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data ///clase de datos, primero se declara para cada una
@Entity /// relacion con entidad
@Table(name = "usuario") /// especificar con tabla relacionada en base de datos
/// implments serializable permite guardar y transmitir datos de los objetos

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L; ///para evitar problemas de compatibilidad entre objetos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///valor para identificar llave
    @Column(name = "id_usuario")
    private Long idUsuario; ///en la tabla tenemos un primary key =idCategoria
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String ruta_imagen;
    private boolean activo;

    //asociacion con tabla //uno a varios
    @OneToMany
    @JoinColumn(name = "id_usuario")
    List<Rol> roles;

    public Object getRutaImagen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Iterable<Rol> getRoles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setRutaImagen(String cargaImagen) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

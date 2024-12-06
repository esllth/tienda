package SC403_tienda.service;

import SC403_tienda.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


public interface RegistroService {

    //model para modifcar en la vista
    public Model activar(Model model, String usuario, String clave);

    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException;

    public void activar(Usuario usuario, MultipartFile imagenFile);

    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException;
}

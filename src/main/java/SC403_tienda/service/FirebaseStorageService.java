package SC403_tienda.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseStorageService {

    ///El buketName es el <id_del_proyecto> + ".appspot.com#
    final String BucketName = "techshop-a4de9.appspot.com";
    ///Esta es la ruta basica de este proyecto
    final String rutaSuperiorStorage = "techshop";
    ///Ubicacion donde se encuentra el archivo de configuracion Json
    final String rutaJsonFile = "firebase";
    ///El nombre del archivo json
    final String archivoJsonFile = "techshop-a4de9-firebase-adminsdk-lihpa-7b72859a36.json";

    //carga imagen
    String cargaImagen(MultipartFile file, String folder, Long idUsuario);
}

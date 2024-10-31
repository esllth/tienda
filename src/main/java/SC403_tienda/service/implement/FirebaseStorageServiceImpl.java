package SC403_tienda.service.implement;

import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;
import SC403_tienda.service.FirebaseStorageService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

//    @Override
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id) {
        try {
            ///nombre original de archivo local cliente
            String extension = archivoLocalCliente.getOriginalFilename();
            ///se genera nombre segun el codigo del articulo
            String fileName = "img" + sacaNumero(id) + extension;
            ///se convierte/sube archivo a archivo temporal
            File file = this.convertToFile(archivoLocalCliente);
            ///se copia a firestore y se obtiene el url valida de imagen -por 10 años-
            String URL = this.uploadFile(file, carpeta, fileName);
            ///se elimina el archivo temporal cargado desde el cliente
            file.delete();
            return URL;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    ///metodo cargar archivo
    private String uploadFile(File file, String carpeta, String fileName) throws IOException {
/// se define lugar y acceso al archivo .jasper
        ClassPathResource json = new ClassPathResource(rutaJsonFile + File.separator + archivoJsonFile);
        BlobId blobId = BlobId.of(BucketName, rutaSuperiorStorage + "/" + carpeta + "/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(json.getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        String url = storage.signUrl(blobInfo,
                3650,
                TimeUnit.DAYS,
                SignUrlOption.signWith((ServiceAccountSigner) credentials)).toString();
        return url;
    }

    ///metodo utilitario que convierte archivo desde equipo local
    ///del usuario a un archivo temporal en el servidor
    private File convertToFile(MultipartFile archivoLocalCliente) throws IOException {
        File tempFile = File.createTempFile("img", null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(archivoLocalCliente.getBytes());
            fos.close();
        }
        return tempFile;
    }

    ///metodo utilitario para obtener string con ceros...
    private String sacaNumero(long id) {
        return String.format("%019d", id);
    }
}

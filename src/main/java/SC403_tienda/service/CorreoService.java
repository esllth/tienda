package SC403_tienda.service;

import jakarta.mail.MessagingException;


public interface CorreoService {

    public void enviarCorreoHtml(
            String para,
            String asunto,
            String contenidoHmtl)
            throws MessagingException;

}

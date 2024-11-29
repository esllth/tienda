package SC403_tienda.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SC403_tienda.service.CorreoService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class CorreoServiceImpl implements CorreoService {

@Autowired
    private JavaMailSender mailSender;

    @Override
      public void enviarCorreoHtml(
              String para, 
              String asunto, 
              String contenidoHtml) 
              throws MessagingException {
          
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper 
                = new MimeMessageHelper(message, 
                        true);
        helper.setTo(para);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml,true);
        mailSender.send(message);
        
    }
}


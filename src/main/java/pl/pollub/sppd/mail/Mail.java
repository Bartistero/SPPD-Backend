package pl.pollub.sppd.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class Mail {

    private final JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String text) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(to);
        helper.setFrom("sppd@server168390.nazwa.pl");
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSender.send(mimeMessage);
    }

    public String registrationTemplate(String token) {

        String template =
                "<h4 style=\"text-align:center;\">Witamy w gronie, posidaczy Portalu Podatkowego</h4>"
                        + "Kliknij w poniższy link aby aktywować swoje konto<br>"
                        + "http://localhost:4200/email/activate?token=" + token
                        + "<br>Ta wiadomoścć została wygenerowana automatycznie, prosimy na nią nie odpowiadać.";
        return template;


    }
}

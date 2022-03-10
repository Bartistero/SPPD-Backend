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

    public void sendMailActivate(String to, String token, String username) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(to);
        helper.setFrom("sppd@server479209.nazwa.pl");
        helper.setSubject("Aktywacja konta");
        helper.setText(registrationTemplate(token, username), true);
        javaMailSender.send(mimeMessage);
    }

    public synchronized void sendRejectMail(String to, String username) throws MessagingException {
        Thread thread = new Thread(() -> {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            try {
                helper.setTo(to);
                helper.setFrom("sppd@server479209.nazwa.pl");
                helper.setSubject("Odrzucenie pracy");
                helper.setText(rejectTemplate(username), true);
            } catch (Exception e) {
                System.out.println("Nastąpił problem z wysyłką maila");
            }
            javaMailSender.send(mimeMessage);
        });
        thread.start();
    }

    private String registrationTemplate(String token, String username) {

        String template =
                "<h4 style=\"text-align:center;\">Witamy Systemie przydału prac dyplomowych i zapisu do promotorów</h4>"
                        + "Kliknij w poniższy link aby aktywować swoje konto<br>"
                        + "http://localhost:4200/email/activate?username=" + username + "&token=" + token
                        + "<br>Ta wiadomoścć została wygenerowana automatycznie, prosimy na nią nie odpowiadać.";
        return template;
    }

    private String rejectTemplate(String username) {
        return "<h4 style=\"text-align:center;\"> Odrzucenie pracy</h4>"
                + "Szanowni Państwo! <br> Zaproponowana przez Państwa praca została odrzucona,"
                + " w celu uzyskania szczegółów, skontaktuj sie z promotorem lub administratorem."
                + "<br style=\"text-align:center;\">Ta wiadomość została wygenerowana automatycznie, prosimy na nią nie odpowiadać.";
    }
}

package com.example.tripmanager.email;

import com.example.tripmanager.entity.User;
import com.example.tripmanager.service.ConfirmationTokenService;
import com.example.tripmanager.service.UserService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailSendgridService {

    @Autowired
    private EmailRequest emailRequest;
    @Autowired
    SendGrid sendGrid;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    public  Response sendEmail(Long id) throws IOException {
        this.emailRequest = this.createEmailRequestForLogin(id);

        Email from = new Email("s210964@student.uek.krakow.pl");
        String subject = this.emailRequest.getSubject();
        Email to = new Email(this.emailRequest.getTo());
        Content content = new Content("text/plain", this.emailRequest.getBody());
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        Response response;

        //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            //Response response = sg.api(request);
            response = this.sendGrid.api(request);
        } catch (IOException ex) {
            throw ex;
        }
        return response;
    }

    private EmailRequest createEmailRequestForLogin(Long id) {
        User user = userService.findUserById(id);
        String token = confirmationTokenService.createConfirmationToken(id);
        String link = "localhost:8080/confirm?token="
                .concat(token)
                .concat("&userId=")
                .concat(user.getId().toString());

        this.emailRequest.setTo(user.getEmail());
        this.emailRequest.setBody(link);
        this.emailRequest.setSubject("Logowanie tripManager");

        return this.emailRequest;
    }
}

package com.waci.erp.shared.utils;

import com.sendgrid.*;
import com.waci.erp.config.EnvironmentConstants;
import com.waci.erp.shared.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author RayGdhrt
 */

@Service
@Slf4j
public class SendGridMailServiceImpl implements MailService{

    private static final Logger logger = LoggerFactory.getLogger(SendGridMailServiceImpl.class);

    public MailSendResponse sendEmail(String[] recievers, String subject, String bodyContent, String[] cc) throws IOException {
        //Create personalisation
        Personalization personalization = new Personalization();
        for (String emailString : recievers) {
            personalization.addTo(new Email(emailString));
        }
        for (String emailString : cc) {
            personalization.addCc(new Email(emailString));
        }
        return send(personalization, subject, bodyContent);
    }

    public MailSendResponse sendEmail(String[] recievers, String subject, String bodyContent) throws IOException {
         //Create personalisation
        Personalization personalization = new Personalization();
        for (String emailString : recievers) {
            personalization.addTo(new Email(emailString));
        }
        return send(personalization, subject, bodyContent);
    }
    public MailSendResponse sendEmail(String reciever, String subject, String bodyContent) {
        //Create personalisation
        Personalization personalization = new Personalization();

            personalization.addTo(new Email(reciever));
        return send(personalization, subject, bodyContent);
    }
    private MailSendResponse send(Personalization personalization, String subject, String bodyContent) {
        Email from = new Email(System.getenv(EnvironmentConstants.MAIL_SENDER_EMAIL));
        Content content = new Content("text/html", bodyContent);

        personalization.addBcc(new Email(System.getenv(EnvironmentConstants.DEFAULT_BCC_EMAIL)));
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(EmailTemplate.buildTeplatedEmail(subject, bodyContent));
        mail.addContent(content);
        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(System.getenv(EnvironmentConstants.MAIL_SENDER_PASS_KEY));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return new MailSendResponse(response.getBody(), true);
        } catch (IOException ex) {
            ex.printStackTrace();
            return new MailSendResponse(ex.getMessage(), false);
        }
    }

}

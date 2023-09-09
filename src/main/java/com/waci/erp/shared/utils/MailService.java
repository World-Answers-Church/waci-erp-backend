package com.waci.erp.shared.utils;

import com.sendgrid.*;
import com.waci.erp.config.EnvironmentConstants;
import com.waci.erp.shared.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

/**
 * @author RayGdhrt
 */


public interface MailService {

    public MailSendResponse sendEmail(String[] recievers, String subject, String bodyContent, String[] cc) throws IOException ;

    public MailSendResponse sendEmail(String[] recievers, String subject, String bodyContent) throws IOException;
    public MailSendResponse sendEmail(String reciever, String subject, String bodyContent);

}

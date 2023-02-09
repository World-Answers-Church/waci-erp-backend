package com.waci.erp.shared.utils;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author RayGdhrt
 */
@Slf4j
public class MailService {

//    private static MailConfig MAIL_CONFIG;
//
//    static void initMailOptions() {
//
//        if (MAIL_CONFIG == null) {
//            log.info("Initialising mail settings...");
//            MAIL_CONFIG = new MailConfig();
//            MAIL_CONFIG.setHostname("smtp.gmail.com");
//            MAIL_CONFIG.setPort(587);
//            MAIL_CONFIG.setStarttls(StartTLSOptions.REQUIRED);
//            MAIL_CONFIG.setUsername("mail.pahappa@gmail.com");
//            MAIL_CONFIG.setPassword("vdtclloeqpwlsprj");
//        }
//
//    }
//
//    public static void sendEmail(String toAddress, String subject, String body, List<String> cc) {
//        initMailOptions();
//        MailClient mailClient = MailClient.create(Vertx.vertx(), MAIL_CONFIG);
//
//        MailMessage message = new MailMessage();
//        message.setFrom("mail.pahappa@gmail.com");
//        message.setTo(toAddress);
//        message.setCc(cc);
//        message.setSubject(subject);
//        message.setHtml(body);
//
//        mailClient.sendMail(message, result -> {
//            if (result.succeeded()) {
//                System.out.println(result.result());
//                System.out.println("Mail sent");
//            } else {
//                System.out.println("got exception");
//                result.cause().printStackTrace();
//            }
//        });
//
//    }
//
//    public static void sendEmails(List<String> toAddresses, String subject, String body, List<String> cc) {
//        initMailOptions();
//        MailClient mailClient = MailClient.create(Vertx.vertx(), MAIL_CONFIG);
//
//        MailMessage message = new MailMessage();
//        message.setFrom("mail.pahappa@gmail.com");
//        message.setTo(toAddresses);
//        message.setCc(cc);
//        message.setSubject(subject);
//        message.setHtml(body);
//
//        mailClient.sendMail(message, result -> {
//            if (result.succeeded()) {
//                System.out.println(result.result());
//                System.out.println("Mail sent");
//            } else {
//                System.out.println("got exception");
//                result.cause().printStackTrace();
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//
//        //Testing it out
//        sendEmail("mutebiraymond695@gmail.com","DALL·E 2 is a new AI system ", EmailTemplate.buildTeplatedEmail("Success email sent", "<h4>DALL·E 2 is a new AI system that can create realistic images and art from a description in natural language.</h4>"), null);
//    }
}

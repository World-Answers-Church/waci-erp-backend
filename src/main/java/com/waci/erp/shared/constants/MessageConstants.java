package com.waci.erp.shared.constants;

public interface MessageConstants {

    String ORGANISATION_REGISTRATION_EMAIL_SUBJECT = "Church Registration Successful";
    String ORGANISATION_REGISTRATION_EMAIL_CONTENT = "<p>Your Church account has been created successfully. You can now log into the church ERP with the details below.</p>\n" +
            "\n" +
            "<p>Church Code: <span style=\"color:#2980b9\"><strong>{0}</strong></span></p>\n" +
            "\n" +
            "<p>Username; <span style=\"color:#2980b9\"><strong>{1}</strong></span></p>\n" +
            "\n" +
            "<p>Password: <span style=\"color:#2980b9\"><strong>{2}</strong></span></p>";
    String NORMAL_SYSTEM_USER = "Normal User";

    //Default Users
    String DEFAULT_ADMIN_USERNAME = "administrator";
    String DEFAULT_ADMIN_PASSWORD = "password123";
}

package by.iba.service;

import by.iba.exception.EmailServiceException;

public interface EmailService {

    void sendEmail(String toAddress, String subject, String message) throws EmailServiceException;

}

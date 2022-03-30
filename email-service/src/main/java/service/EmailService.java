package service;

public interface EmailService {

    void sendEmail(String toAddress, String subject, String message);

}

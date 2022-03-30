package by.iba.config;

import by.iba.entity.user.PasswordRecoveryCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import service.EmailService;
import service.impl.DefaultEmailService;

@Configuration
public class PasswordRecoveryConfig {
    @Bean
    public PasswordRecoveryCode passwordRecoveryCode() {
        final PasswordRecoveryCode passwordRecoveryCode = new PasswordRecoveryCode("","");
        return passwordRecoveryCode;
    }
}

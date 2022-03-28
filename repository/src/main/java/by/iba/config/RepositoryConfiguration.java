package by.iba.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "by.iba.repository")
@EntityScan(basePackages = "by.iba.entity")
@EnableTransactionManagement
public class RepositoryConfiguration {
}

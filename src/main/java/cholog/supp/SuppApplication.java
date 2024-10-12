package cholog.supp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SuppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuppApplication.class, args);
    }

}

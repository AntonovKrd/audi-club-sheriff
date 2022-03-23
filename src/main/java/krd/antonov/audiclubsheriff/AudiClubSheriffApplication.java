package krd.antonov.audiclubsheriff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AudiClubSheriffApplication {

    public static void main(String[] args) {
        SpringApplication.run(AudiClubSheriffApplication.class, args);
    }

}

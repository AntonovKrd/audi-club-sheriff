package krd.antonov.audiclubsheriff;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@UnitTest
class AudiClubSheriffApplicationTests {

    @Test
    @DisplayName("Application starter properly configured")
    public void application_configured() {
        Assertions.assertThat(AudiClubSheriffApplication.class).
                hasMethods("main").
                hasAnnotations(SpringBootApplication.class).
                hasAnnotations(ConfigurationPropertiesScan.class);
    }

}

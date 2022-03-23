package krd.antonov.audiclubsheriff.telegram.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfigurationProperties(prefix = "telegram")
@Validated
public class BotPropertiesConfiguration {

    @NotBlank
    private String apiUrl;

    @NotBlank
    private String botName;

    @NotBlank
    private String botToken;

    @NotBlank
    private String webhookPath;
}

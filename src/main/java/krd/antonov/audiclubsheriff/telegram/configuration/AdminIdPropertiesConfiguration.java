package krd.antonov.audiclubsheriff.telegram.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfigurationProperties(prefix = "telegram.chat.id")
@Validated
public class AdminIdPropertiesConfiguration {

    @NotBlank
    private String developer;

    @NotBlank
    private String admin;
}

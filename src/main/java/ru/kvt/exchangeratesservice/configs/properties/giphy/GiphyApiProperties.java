package ru.kvt.exchangeratesservice.configs.properties.giphy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "giphy-api")
@ConfigurationPropertiesScan
@Configuration
public class GiphyApiProperties {

    private String url;

    @NotNull
    private String apiKey;

}

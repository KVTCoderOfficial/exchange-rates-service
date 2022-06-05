package ru.kvt.exchangeratesservice.configs.properties.giphy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "giphy-media")
@ConfigurationPropertiesScan
@Configuration
public class GiphyMediaProperties {

    private String url;

}

package ru.kvt.exchangeratesservice.configs.properties.openexchangerates;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "open-exchange-rates-api")
@ConfigurationPropertiesScan
@Configuration
public class OpenExchangeRatesApiProperties {

    private String url;

    @NotNull
    private String appId;

}

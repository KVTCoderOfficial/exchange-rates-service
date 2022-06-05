package ru.kvt.exchangeratesservice.configs.properties.currency;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "currency")
@ConfigurationPropertiesScan
@Configuration
public class CurrencyProperties {

    private String compareCurrency;

}

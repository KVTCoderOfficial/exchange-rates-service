package ru.kvt.exchangeratesservice.configs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import ru.kvt.exchangeratesservice.configs.properties.currency.CurrencyProperties;
import ru.kvt.exchangeratesservice.configs.properties.giphy.GiphyApiProperties;
import ru.kvt.exchangeratesservice.configs.properties.giphy.GiphyMediaProperties;
import ru.kvt.exchangeratesservice.configs.properties.openexchangerates.OpenExchangeRatesApiProperties;

@ConfigurationProperties(prefix = "exchange-rates-service")
@ConfigurationPropertiesScan
@Configuration
public class ExchangeRatesServiceProperties {

    private GiphyApiProperties giphyApiProperties;

    private GiphyMediaProperties giphyMediaProperties;

    private OpenExchangeRatesApiProperties openExchangeRatesApiProperties;

    private CurrencyProperties currencyProperties;

}

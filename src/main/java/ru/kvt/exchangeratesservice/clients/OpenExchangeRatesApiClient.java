package ru.kvt.exchangeratesservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kvt.exchangeratesservice.dto.openexchangerates.OpenExchangeRatesApiResponseDto;

@FeignClient(name = "openexchangeratesapi",
        url = "${exchange-rates-service.open-exchange-rates-api.url}"
)
public interface OpenExchangeRatesApiClient {

    @GetMapping("/latest.json?app_id=${exchange-rates-service.open-exchange-rates-api.app-id}&base=${exchange-rates-service.currency.compare-currency}")
    OpenExchangeRatesApiResponseDto getLatest();

    @GetMapping("/historical/{date}.json?app_id=${exchange-rates-service.open-exchange-rates-api.app-id}&base=${exchange-rates-service.currency.compare-currency}")
    OpenExchangeRatesApiResponseDto getByDate(@PathVariable String date);

}

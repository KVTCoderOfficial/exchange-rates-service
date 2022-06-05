package ru.kvt.exchangeratesservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kvt.exchangeratesservice.dto.giphy.GiphyApiResponseDto;

@FeignClient(name = "giphyapi",
        url = "${exchange-rates-service.giphy-api.url}"
)
public interface GiphyApiClient {

    @GetMapping("/random?api_key=${exchange-rates-service.giphy-api.api-key}&tag=rich")
    GiphyApiResponseDto getRandomRichGif();

    @GetMapping("/random?api_key=${exchange-rates-service.giphy-api.api-key}&tag=broke")
    GiphyApiResponseDto getRandomBrokeGif();

}

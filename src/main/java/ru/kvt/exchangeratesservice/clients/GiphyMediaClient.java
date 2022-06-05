package ru.kvt.exchangeratesservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "giphymedia",
        url = "${exchange-rates-service.giphy-media.url}"
)
public interface GiphyMediaClient {

    @GetMapping
    ByteArrayResource downloadGif(URI downloadUri);

}

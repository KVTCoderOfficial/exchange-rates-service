package ru.kvt.exchangeratesservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;
import ru.kvt.exchangeratesservice.model.Currency;
import ru.kvt.exchangeratesservice.services.RateService;

@RestController
@RequestMapping("/api/v1/exchange-rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping(value = "/dynamic-gif", produces = "image/gif")
    public ByteArrayResource getRateDynamicGif(@RequestParam Currency currency) {
        return rateService.getRateDynamicGif(currency);
    }

}

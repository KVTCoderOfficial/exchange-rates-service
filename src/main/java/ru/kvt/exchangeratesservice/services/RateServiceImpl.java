package ru.kvt.exchangeratesservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import ru.kvt.exchangeratesservice.clients.GiphyApiClient;
import ru.kvt.exchangeratesservice.clients.GiphyMediaClient;
import ru.kvt.exchangeratesservice.clients.OpenExchangeRatesApiClient;
import ru.kvt.exchangeratesservice.model.Currency;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final OpenExchangeRatesApiClient openExchangeRatesApiClient;

    private final GiphyApiClient giphyApiClient;

    private final GiphyMediaClient giphyMediaClient;

    @Override
    public ByteArrayResource getRateDynamicGif(Currency currency) {
        BigDecimal latestRate = openExchangeRatesApiClient.getLatest().getRates().get(currency.toString());
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        BigDecimal yesterdayRate = openExchangeRatesApiClient.getByDate(yesterdayDate.toString()).getRates().get(currency.toString());
        if (latestRate.compareTo(yesterdayRate) < 0) {
            URI brokeGifDownloadUri = URI.create(giphyApiClient.getRandomBrokeGif().getData().getImages().getOriginal().getUrl());
            return giphyMediaClient.downloadGif(brokeGifDownloadUri);
        }
        URI richGifDownloadUri = URI.create(giphyApiClient.getRandomRichGif().getData().getImages().getOriginal().getUrl());
        return giphyMediaClient.downloadGif(richGifDownloadUri);
    }

}

package ru.kvt.exchangeratesservice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.kvt.exchangeratesservice.IntegrationTest;
import ru.kvt.exchangeratesservice.clients.GiphyApiClient;
import ru.kvt.exchangeratesservice.clients.GiphyMediaClient;
import ru.kvt.exchangeratesservice.clients.OpenExchangeRatesApiClient;
import ru.kvt.exchangeratesservice.dto.giphy.GifObjectDto;
import ru.kvt.exchangeratesservice.dto.giphy.GiphyApiResponseDto;
import ru.kvt.exchangeratesservice.dto.giphy.ImagesObjectDto;
import ru.kvt.exchangeratesservice.dto.giphy.OriginalObjectDto;
import ru.kvt.exchangeratesservice.dto.openexchangerates.OpenExchangeRatesApiResponseDto;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RateControllerIntegrationTest extends IntegrationTest {

    private static final String CONTEXT_PATH = "/exchange-rates-service";

    private static final String BASE_URL = "/api/v1/exchange-rates";

    @LocalServerPort
    protected String port;

    @Autowired
    protected TestRestTemplate restTemplate;

    @MockBean
    private GiphyApiClient giphyApiClient;

    @MockBean
    private GiphyMediaClient giphyMediaClient;

    @MockBean
    private OpenExchangeRatesApiClient openExchangeRatesApiClient;

    @Test
    @DisplayName("Получение gif, если курс стал ниже вчерашнего")
    public void getGifWhenLatestRateLessThanYesterdayRate() throws Exception {
        OpenExchangeRatesApiResponseDto latestOpenExchangeRatesApiResponseDto = new OpenExchangeRatesApiResponseDto();
        Map<String, BigDecimal> latestRates = new HashMap<>();
        latestRates.put("USD", BigDecimal.valueOf(0.99));
        latestOpenExchangeRatesApiResponseDto.setRates(latestRates);
        Mockito.doReturn(latestOpenExchangeRatesApiResponseDto).when(openExchangeRatesApiClient).getLatest();

        OpenExchangeRatesApiResponseDto yesterdayOpenExchangeRatesApiResponseDto = new OpenExchangeRatesApiResponseDto();
        Map<String, BigDecimal> yesterdayRates = new HashMap<>();
        yesterdayRates.put("USD", BigDecimal.valueOf(1));
        yesterdayOpenExchangeRatesApiResponseDto.setRates(yesterdayRates);
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        Mockito.doReturn(yesterdayOpenExchangeRatesApiResponseDto).when(openExchangeRatesApiClient).getByDate(yesterdayDate.toString());

        OriginalObjectDto originalObjectDto = new OriginalObjectDto();
        originalObjectDto.setUrl("https://test.com/test");
        ImagesObjectDto imagesObjectDto = new ImagesObjectDto();
        imagesObjectDto.setOriginal(originalObjectDto);
        GifObjectDto gifObjectDto = new GifObjectDto();
        gifObjectDto.setImages(imagesObjectDto);
        GiphyApiResponseDto giphyApiResponseDto = new GiphyApiResponseDto();
        giphyApiResponseDto.setData(gifObjectDto);
        Mockito.doReturn(giphyApiResponseDto).when(giphyApiClient).getRandomBrokeGif();

        byte[] byteGif = new byte[]{1, 1, 1, 1, 1, 1, 1};
        ByteArrayResource brokeGif = new ByteArrayResource(byteGif, "broke");
        URI testUri = URI.create("https://test.com/test");
        Mockito.doReturn(brokeGif).when(giphyMediaClient).downloadGif(testUri);

        ResponseEntity<ByteArrayResource> actual = restTemplate.getForEntity("http://localhost:" + port + CONTEXT_PATH + BASE_URL + "/dynamic-gif?currency=USD", ByteArrayResource.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).isEqualTo(brokeGif);
    }

    @Test
    @DisplayName("Получение gif, если курс стал выше вчерашнего")
    public void getGifWhenLatestRateGreaterThanYesterdayRate() throws Exception {
        OpenExchangeRatesApiResponseDto latestOpenExchangeRatesApiResponseDto = new OpenExchangeRatesApiResponseDto();
        Map<String, BigDecimal> latestRates = new HashMap<>();
        latestRates.put("USD", BigDecimal.valueOf(1));
        latestOpenExchangeRatesApiResponseDto.setRates(latestRates);
        Mockito.doReturn(latestOpenExchangeRatesApiResponseDto).when(openExchangeRatesApiClient).getLatest();

        OpenExchangeRatesApiResponseDto yesterdayOpenExchangeRatesApiResponseDto = new OpenExchangeRatesApiResponseDto();
        Map<String, BigDecimal> yesterdayRates = new HashMap<>();
        yesterdayRates.put("USD", BigDecimal.valueOf(0.99));
        yesterdayOpenExchangeRatesApiResponseDto.setRates(yesterdayRates);
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        Mockito.doReturn(yesterdayOpenExchangeRatesApiResponseDto).when(openExchangeRatesApiClient).getByDate(yesterdayDate.toString());

        OriginalObjectDto originalObjectDto = new OriginalObjectDto();
        originalObjectDto.setUrl("https://test.com/test");
        ImagesObjectDto imagesObjectDto = new ImagesObjectDto();
        imagesObjectDto.setOriginal(originalObjectDto);
        GifObjectDto gifObjectDto = new GifObjectDto();
        gifObjectDto.setImages(imagesObjectDto);
        GiphyApiResponseDto giphyApiResponseDto = new GiphyApiResponseDto();
        giphyApiResponseDto.setData(gifObjectDto);
        Mockito.doReturn(giphyApiResponseDto).when(giphyApiClient).getRandomRichGif();

        byte[] byteGif = new byte[]{1, 1, 1, 1, 1, 1, 1};
        ByteArrayResource richGif = new ByteArrayResource(byteGif, "rich");
        URI testUri = URI.create("https://test.com/test");
        Mockito.doReturn(richGif).when(giphyMediaClient).downloadGif(testUri);

        ResponseEntity<ByteArrayResource> actual = restTemplate.getForEntity("http://localhost:" + port + CONTEXT_PATH + BASE_URL + "/dynamic-gif?currency=USD", ByteArrayResource.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).isEqualTo(richGif);
    }

    @Test
    @DisplayName("Получение gif, если курс равен вчерашнему")
    public void getGifWhenLatestRateEqualsYesterdayRate() throws Exception {
        OpenExchangeRatesApiResponseDto latestOpenExchangeRatesApiResponseDto = new OpenExchangeRatesApiResponseDto();
        Map<String, BigDecimal> latestRates = new HashMap<>();
        latestRates.put("USD", BigDecimal.valueOf(1));
        latestOpenExchangeRatesApiResponseDto.setRates(latestRates);
        Mockito.doReturn(latestOpenExchangeRatesApiResponseDto).when(openExchangeRatesApiClient).getLatest();

        OpenExchangeRatesApiResponseDto yesterdayOpenExchangeRatesApiResponseDto = new OpenExchangeRatesApiResponseDto();
        Map<String, BigDecimal> yesterdayRates = new HashMap<>();
        yesterdayRates.put("USD", BigDecimal.valueOf(1));
        yesterdayOpenExchangeRatesApiResponseDto.setRates(yesterdayRates);
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        Mockito.doReturn(yesterdayOpenExchangeRatesApiResponseDto).when(openExchangeRatesApiClient).getByDate(yesterdayDate.toString());

        OriginalObjectDto originalObjectDto = new OriginalObjectDto();
        originalObjectDto.setUrl("https://test.com/test");
        ImagesObjectDto imagesObjectDto = new ImagesObjectDto();
        imagesObjectDto.setOriginal(originalObjectDto);
        GifObjectDto gifObjectDto = new GifObjectDto();
        gifObjectDto.setImages(imagesObjectDto);
        GiphyApiResponseDto giphyApiResponseDto = new GiphyApiResponseDto();
        giphyApiResponseDto.setData(gifObjectDto);
        Mockito.doReturn(giphyApiResponseDto).when(giphyApiClient).getRandomRichGif();

        byte[] byteGif = new byte[]{1, 1, 1, 1, 1, 1, 1};
        ByteArrayResource richGif = new ByteArrayResource(byteGif, "rich");
        URI testUri = URI.create("https://test.com/test");
        Mockito.doReturn(richGif).when(giphyMediaClient).downloadGif(testUri);

        ResponseEntity<ByteArrayResource> actual = restTemplate.getForEntity("http://localhost:" + port + CONTEXT_PATH + BASE_URL + "/dynamic-gif?currency=USD", ByteArrayResource.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).isEqualTo(richGif);
    }

}

package ru.kvt.exchangeratesservice.services;

import org.springframework.core.io.ByteArrayResource;
import ru.kvt.exchangeratesservice.model.Currency;

public interface RateService {

    ByteArrayResource getRateDynamicGif(Currency currency);

}

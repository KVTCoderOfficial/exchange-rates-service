package ru.kvt.exchangeratesservice.dto.errors;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    INVALID_REQUEST_PARAMS("Неверные параметры запроса"),
    INVALID_CREDENTIALS_FOR_EXTERNAL_SERVICES("Некорректные реквизиты для входа на сторонние сервисы"),
    UNEXPECTED_ERROR("Неизвестная ошибка");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}

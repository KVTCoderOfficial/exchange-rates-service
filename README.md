# exchange-rates-service
Сервис, который обращается к сервису курсов валют, и отображает gif.

Если курс по отношению к выбранной валюте (по умолчанию USD) за сегодня равен или стал выше вчерашнего, то отдается рандомная отсюда https://giphy.com/search/rich

если ниже - отсюда https://giphy.com/search/broke

REST API курсов валют - https://docs.openexchangerates.org/

REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide


Для запуска программы:

1)Сделайте сборку проекта (gradle build), проект будет находиться в директории \build\libs с названием exchange-rates-service-0.0.1-SNAPSHOT.jar;

2)Запустите докер и введите следующие команды, указав начальные параметры:

Параметры:

GIPHY_API_KEY=(API Key, который выдается при регистрации на сайте https://developers.giphy.com) - обязательный параметр

OPEN_EXCHANGE_RATES_API_APP_ID=(App ID, который выдается при регистрации на сайте https://docs.openexchangerates.org) - обязательный параметр

GIPHY_API_URL=(адрес АПИ сайта гифок) - необязательный параметр, по умолчанию равен https://api.giphy.com/v1/gifs

OPEN_EXCHANGE_RATES_API_URL=(адрес АПИ сайта курсов валют) - необязательный параметр, по умолчанию равен https://openexchangerates.org/api

COMPARE_CURRENCY=(валюта, по отношению к которой смотрится курс) - необязательный параметр, по умолчанию равен USD

Команды:

а)docker build -t exchange-rates-service .

б)docker run -p 8189:8189 -d -e GIPHY_API_KEY=(API Key) -e OPEN_EXCHANGE_RATES_API_APP_ID=(App ID) exchange-rates-service

Со всеми параметрами:

docker run -p 8189:8189 -d -e GIPHY_API_KEY=(API Key) -e OPEN_EXCHANGE_RATES_API_APP_ID=(App ID) -e GIPHY_API_URL=(API Url) -e OPEN_EXCHANGE_RATES_API_URL=(API Url) -e COMPARE_CURRENCY=(Currency) exchange-rates-service


Для запуска программы без докера:

1)Сделайте сборку проекта (gradle build), проект будет находиться в директории \build\libs с названием exchange-rates-service-0.0.1-SNAPSHOT.jar;

2)Запустите приложение, указав начальные параметры

При использовании консоли, зайдите в папку с файлом exchange-rates-service-0.0.1-SNAPSHOT.jar и выполните команду

java -jar -DGIPHY_API_KEY=(API Key) -DOPEN_EXCHANGE_RATES_API_APP_ID=(App ID) exchange-rates-service-0.0.1-SNAPSHOT.jar

Со всеми параметрами:

java -jar -DGIPHY_API_KEY=(API Key) -DOPEN_EXCHANGE_RATES_API_APP_ID=(App ID) -DGIPHY_API_URL=(API Url) -DOPEN_EXCHANGE_RATES_API_URL=(API Url) -DCOMPARE_CURRENCY=(Currency) exchange-rates-service-0.0.1-SNAPSHOT.jar

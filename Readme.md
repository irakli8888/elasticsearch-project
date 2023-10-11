
# Название проекта

The Elasticsearch Spring

## Описание проекта

Демонстрационное приложение для изучения технологии Elasticsearch и применения ее вместе с фреймворком Spring. Приложение использует Java 11, Spring Boot 2.3.6, Elasticsearch 7.10.1, PostgreSQL 12.16, Kibana 7.10.1 и Logstash-OSS 7.9.1.

## Архитектура

Приложение построено с использованием типичной MVC архитектуры для небольших Spring Boot приложений.

## Как начать работу с проектом

1. Установите Docker и Docker Compose.

2. Склонируйте репозиторий проекта:
```
git clone https://github.com/irakli8888/elasticsearch-project.git
```

3. Подготовьте окружение с помощью Docker Compose:
```
cd elasticsearch-project
docker-compose up -d
```
## Файл `docker-compose.yml`

Для упрощения запуска приложения и его зависимостей мы используем `docker-compose`, чтобы создать и настроить контейнеры, необходимые для работы приложения.

Файл `docker-compose.yml` содержит определение четырех сервисов:
- `db`: контейнер PostgreSQL, необходимый для хранения данных приложения;
- `odfe-node`: контейнер Elasticsearch, необходимый для поиска по данным приложения;
- `kibana`: контейнер Kibana, который используется для визуализации данных Elasticsearch;
- `logstash`: контейнер Logstash, который используется для сбора и отправки логов приложения в Elasticsearch.

## Как использовать `docker-compose`

1. Убедитесь, что у вас установлен Docker и Docker Compose.
2. Склонируйте репозиторий проекта:
```
git clone https://github.com/irakli8888/elasticsearch-project.git
```
3. Перейдите в директорию проекта:
```
cd elasticsearch-project
```
4. Запустите контейнеры с помощью команды:
```
docker-compose up -d
```
5. Проверьте, что все контейнеры успешно запустились, выполнив команду:
```
docker-compose ps
```
Вы должны увидеть следующее:
```
        Name                      Command               State           Ports
----------------------------------------------------------------------------------------
elasticsearch-spring-demo_db_1      docker-entrypoint.sh postgres    Up      0.0.0.0:5433->5432/tcp
elasticsearch-spring-demo_kibana_1  /usr/local/bin/dumb-init  ...   Up      0.0.0.0:5601->5601/tcp
elasticsearch-spring-demo_logstash_1 /usr/local/bin/docker-entr ...   Up      0.0.0.0:5044->5044/tcp
elasticsearch-spring-demo_odfe-node_1 /usr/local/bin/docker-entr ...   Up      0.0.0.0:9200->9200/tcp, 0.0.0.0:9600->9600/tcp
```
6. Вы можете остановить контейнеры с помощью команды:
```
docker-compose down
```

Это позволит вам быстро и просто развернуть окружение для тестирования и использования вашего приложения.
4. Соберите и запустите приложение с помощью Maven:
```
cd demo
mvn spring-boot:run
```

5. Проверьте, что приложение успешно запустилось, открыв http://localhost:8080 в браузере.

## Как использовать приложение

Вы можете работать с приложением через REST API или через UI.

### REST API

#### Добавление массива данных в индекс tickets

Запрос:
```
POST http://localhost:8080/api/v1/tickets/repo/bulk
Content-Type: application/json
```

Тело:
```
{
  "tickets": [
    {
      "ticket_no": "1234555",
      "bookRef": "ABC123",
      "passengerId": "P12ssssssss3",
      "passengerName": "John Doe"
    },
    {
      "ticket_no": "67890",
      "bookRef": "DEF456",
      "passengerId": "P456",
      "passengerName": "Jane Smith"
    }
  ]
}
```

#### Добавление в индекс одного элемента 

Запрос:
```
POST http://localhost:8080/api/v1/tickets/repo
Content-Type: application/json
```
Тело:
```
{
  "ticketDto": {
    "ticket_no": "12345",
    "bookRef": "ABC123",
    "passengerId": "P123",
    "passengerName": "John Doe"
  }
}
```

#### Поиск по имени (репозиторий)

Запрос:
```
GET http://localhost:8080/api/v1/tickets/repo?name=MAKSIM
Content-Type: application/json
```

#### Поиск по имени (native)
```
GET http://localhost:8080/api/v1/tickets/native?q=MAKSIM
Content-Type: application/json
```

### UI

1. Откройте http://localhost:8080/search в браузере.

2. На главной странице вы поисковую строку.

6. Чтобы искать пассажиров по имени, введите их в строку поиска и нажмите на кнопку "Search", поддерживается функция автодополнения.

## Дополнительные материалы

Для более подробной информации об Elasticsearch и Spring вы можете обратиться к следующим ресурсам:

- [Официальная документация Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
- [Официальная документация Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.x/reference/html/)
- [Spring Data Elasticsearch Reference Guide](https://docs.spring.io/spring-data/elasticsearch/docs/4.1.15/reference/html/)
- [Elastic Stack: все, что нужно для эффективного использования Elasticsearch и Kibana](https://www.elastic.co/what-is/elk-stack)

# Library Management System (Spring Boot + PostgreSQL + Swagger)

## Описание

Это RESTful приложение для управления библиотекой. Реализовано с использованием Spring Boot, Hibernate, PostgreSQL и OpenAPI (Swagger UI).

Проект поддерживает CRUD-операции над сущностями:

- Book
- Author
- Publisher

Дополнительно в базе участвуют сущности `Category` и `Library`, связанные с книгами, но напрямую не управляются контроллерами.

## Архитектура проекта

```
src/
├── controller/
│   ├── AuthorController.java
│   ├── BookController.java
│   └── PublisherController.java
├── dto/
│   ├── AuthorDTO.java
│   ├── BookDTO.java
│   ├── PublisherDTO.java
│   ├── CategoryDTO.java
│   └── LibraryDTO.java
├── entity/
│   ├── Author.java
│   ├── Book.java
│   ├── Publisher.java
│   ├── Category.java
│   └── Library.java
├── repository/
│   ├── AuthorRepository.java
│   ├── BookRepository.java
│   ├── PublisherRepository.java
│   ├── CategoryRepository.java
│   └── LibraryRepository.java
├── services/
│   ├── AuthorService.java
│   ├── BookService.java
│   └── PublisherService.java
└── LibmanApplication.java
```

## Технологии

- Java 17
- Spring Boot 3.2
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Swagger (Springdoc OpenAPI)

## Основная логика

### Book

- Каждая книга связана с одним автором и одним издателем (Many-to-One).
- Одна книга может принадлежать к нескольким категориям (Many-to-Many).

### Author

- Один автор может написать множество книг (One-to-Many).

### Publisher

- Один издатель может выпустить множество книг (One-to-Many).

## DTO вместо Entity

Контроллеры обмениваются данными только через DTO-объекты. Entity используются только внутри слоёв `service` и `repository`.

## Swagger UI

Для тестирования API используется Swagger:

- Открой в браузере: http://localhost:8080/swagger-ui/index.html
- Доступны все методы: GET, POST, PUT, DELETE для /books, /authors, /publishers

## Конфигурация

### application.properties

```
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Запуск

1. Убедился, что PostgreSQL запущен и создана БД `springlibrary`:

2. Открываю Swagger UI:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## Примеры запросов

### POST /books

```json
{
  "title": "Java 21",
  "authorId": 1,
  "publisherId": 1,
  "categoryIds": [1, 2]
}
```

## Автор

Разработано в рамках второй лабораторной работы по Java Spring Boot.  
Автор: Славов Константин, группа I2302

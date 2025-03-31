# Library API (Spring Boot + PostgreSQL)

## Описание

Приложение для управления библиотекой, реализованное с использованием Spring Boot, JPA и PostgreSQL. Позволяет выполнять CRUD-операции с книгами, авторами и библиотеками.

## Сущности

- Author — авторы книг
- Book — книги
- Publisher — издатели
- Category — категории книг
- Library — библиотека, содержащая список книг (ElementCollection)

## Архитектура проекта

```
src/
├── controller/
│   ├── AuthorController.java
│   ├── BookController.java
│   └── LibraryController.java
├── dto/
│   ├── AuthorDTO.java
│   ├── BookDTO.java
│   ├── CategoryDTO.java
│   ├── LibraryDTO.java
│   └── PublisherDTO.java
├── entity/
│   ├── Author.java
│   ├── Book.java
│   ├── Category.java
│   ├── Library.java
│   └── Publisher.java
├── SpringLibraryApplication.java
```

## Используемые технологии

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven

## Конфигурация

### application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Запуск проекта

1. Убедитесь, что PostgreSQL установлен и создана база данных `library`:

```sql
CREATE DATABASE library;
```

2. Соберите и запустите проект:

```bash
mvn spring-boot:run
```

3. Приложение будет доступно по адресу:

```
http://localhost:8080
```

## Swagger UI (если подключен)

Swagger UI будет доступен по адресу:

```
http://localhost:8080/swagger-ui/index.html
```

## Примеры API

### POST /books

```json
{
  "title": "Spring Fundamentals",
  "authorId": 1,
  "publisherId": 1,
  "categoryIds": [1, 2]
}
```

## Автор

Разработано в рамках учебной лабораторной работы по Spring Boot.

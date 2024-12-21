# REST API для управления новостями и категориями

## Обзор

Этот проект представляет собой простой REST API для управления категориями и новостными статьями. Приложение поддерживает основные операции CRUD для **Категорий** и **Новостей**. Категории и новости — это связанные сущности, где каждая новость относится к определенной категории. API позволяет:

- Создавать, просматривать, обновлять и удалять категории.
- Создавать, просматривать, обновлять и удалять новости.
- Получать список новостей по категории.

Приложение построено с использованием **Spring Boot**, **Spring Data JPA** и **Hibernate** для ORM. Обработка исключений централизована с помощью `@RestControllerAdvice`.

## Содержание

- [Технологии](#технологии)
- [Архитектура](#архитектура)
- [Конечные точки](#конечные-точки)
- [Сущности](#сущности)
- [Обработка исключений](#обработка-исключений)
- [Начало работы](#начало-работы)

---

## Технологии

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Lombok**
- **Jackson** (для сериализации/десериализации JSON)
- **Liquibase** (для миграции баз данных и лёгкого управления изменениями схемы базы в процессе разработки)
- **Maven** (для управления зависимостями)

---

## Архитектура

- **Контроллеры**: Обрабатывают HTTP-запросы и возвращают ответы в формате JSON.
- **Сервисы**: Содержат бизнес-логику для категорий и новостей.
- **Репозитории**: Используют Spring Data JPA для взаимодействия с базой данных.
- **DTO**: Объекты передачи данных для общения между контроллерами и сервисами.
- **Мапперы**: Преобразуют сущности в DTO и обратно.
- **Сущности**: Представляют модели базы данных для категорий и новостей.
- **Обработка исключений**: Централизованная обработка ошибок с помощью `@RestControllerAdvice`.

---

## Конечные точки

### Категории

| Метод    | URL                      | Описание                       | Код ответа           |
|----------|---------------------------|---------------------------------|----------------------|
| `GET`    | `/categories/{id}`         | Получить категорию по ID        | `200 OK`             |
| `GET`    | `/categories`              | Получить список всех категорий  | `200 OK`             |
| `POST`   | `/categories`              | Создать новую категорию         | `201 Created`        |
| `PUT`    | `/categories`              | Обновить существующую категорию | `200 OK`             |
| `DELETE` | `/categories/{id}`         | Удалить категорию по ID         | `204 No Content`     |

### Новости

| Метод    | URL                          | Описание                       | Код ответа           |
|----------|------------------------------|---------------------------------|----------------------|
| `GET`    | `/news/{id}`                  | Получить новость по ID          | `200 OK`             |
| `GET`    | `/news`                       | Получить список всех новостей   | `200 OK`             |
| `GET`    | `/news/category/{id}`         | Получить новости по категории   | `200 OK`             |
| `POST`   | `/news`                       | Создать новую новость           | `201 Created`        |
| `PUT`    | `/news`                       | Обновить существующую новость   | `200 OK`             |
| `DELETE` | `/news/{id}`                  | Удалить новость по ID           | `204 No Content`     |

---

## Сущности

### Категория

- **Поля**:
    - `id` (Long): Уникальный идентификатор.
    - `title` (String): Название категории.
    - `newsList` (List\<News\>): Отношение "один ко многим" с новостями.

### Новость

- **Поля**:
    - `id` (Long): Уникальный идентификатор.
    - `title` (String): Заголовок новости.
    - `text` (String): Текст новости.
    - `date` (Instant): Дата создания новости.
    - `category` (Category): Отношение "многие к одному" с категорией.

---

## Обработка исключений

Проект содержит пользовательские исключения для случаев, когда ресурс не найден:
- `CategoryNotFoundException`: Выбрасывается, когда категория не найдена.
- `NewsNotFoundException`: Выбрасывается, когда новость не найдена.

Эти исключения обрабатываются глобально с помощью `GlobalExceptionHandler`, который возвращает понятное сообщение об ошибке с соответствующим кодом ответа.

Пример ответа при отсутствии категории:
```json
{
  "message": "Категория с id 1 не найдена."
}
```
## Начало работы

### Требования

- Java 21
- Maven 3.9+
- База данных PostgreSQL или MySQL (или другая поддерживаемая СУБД)


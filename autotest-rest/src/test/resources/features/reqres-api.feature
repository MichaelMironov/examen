#language: ru

@api @reqres
@severity=trivial
Функционал: Проверка api reqres

  Сценарий: Отправка POST запроса json телом
  - Создание контекстных переменных для json тела
  - Формирование запроса
  - Отправка json тела
  - Проверка успешного выполнения запроса
  - Проверка извлеченных данных
    Дано создать контекстные переменные
      | name | Potato    |
      | job  | Eat Maket |
    Тогда создать запрос
      | method | url                         | body        |
      | POST   | https://reqres.in/api/users | potato.json |
    Также добавить header
      | Content-Type | application/json |
    Если отправить запрос
    То статус код 201
    Тогда извлечь данные
      | actual_name | $.name |
      | actual_job  | $.job  |
      | actual_id   | $.id   |
    И сравнить значения
      | ${name} | == | ${actual_name} |
      | ${job}  | == | ${actual_job}  |

  @severity=minor
  Сценарий: Отправка json тела в POST запрос с случайными занечиями
  - Геренация переменных для json тела
  - Формирование запроса
  - Отправка json тела
  - Проверка успешного выполнения запроса
  - Проверка извлеченных данных

    Дано сгенерировать переменные
      | name | EEEEE    |
      | job  | DDD EEEE |
    Тогда создать запрос
      | method | url                         | body        |
      | POST   | https://reqres.in/api/users | potato.json |
    Также добавить header
      | Content-Type | application/json |
    Если отправить запрос
    То статус код 201
    Тогда извлечь данные
      | actual_name | $.name |
      | actual_job  | $.job  |
      | actual_id   | $.id   |
    И сравнить значения
      | ${name} | == | ${actual_name} |
      | ${job}  | == | ${actual_job}  |


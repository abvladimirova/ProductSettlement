# Проект в рамках обучения T1 "Разработка на Java. Практика (Переобучение ЦФТ, 2-й этап). Поток 2_6"
# "Задание №5. Разворачивание сервиса"

Запустить приложение с БД в Docker:
docker-compose up -d --build

liquibase.enabled=true
liquibase.url=${POSTGRES_JDBC_URL}
liquibase.user=${POSTGRES_USERNAME}
liquibase.password=${POSTGRES_PASSWORD}
liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
_______________________________________

## 1. Создание Экземпляра Продукта (ЭП) - **corporate-settlement-instance/create**
json-example/corporate-settlement-instance-create.json

## 2. Создание Доп. соглашения к ЭП - **corporate-settlement-instance/create**
json-example/corporate-settlement-instance-create-agr.json

## 3. Создание Продуктового Регистра (ПР) к ЭП - **corporate-settlement-account/create**
json-example/corporate-settlement-account-create.json






# Проект в рамках обучения T1 "Разработка на Java. Практика (Переобучение ЦФТ, 2-й этап). Поток 2_6"
# "Задание №5. Разворачивание сервиса"

1. Создать образ проекта для Docker:
docker build -t product_settlement:v1 .

2. Запустить приложение с БД в Docker:
docker-compose up --detach --build
docker-compose up -d --build

3. Остановить 
docker-compose down
_________________________________

liquibase.enabled=true
liquibase.url=${POSTGRES_JDBC_URL}
liquibase.user=${POSTGRES_USERNAME}
liquibase.password=${POSTGRES_PASSWORD}
liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
_______________________________________

## 1. Создание Экземпляра Продукта (ЭП) - **corporate-settlement-instance/create**

    {
    "instanceId": 1,
    "productType": "НСО",
    "productCode": 1,
    "registerType": "type",
    "mdmCode": "mdmCode",
    "contractNumber": "N123",
    "contractDate": "2024-07-02",
    "priority": 1
    }

## 2. Создание Доп. соглашения к ЭП - **corporate-settlement-instance/create**

    {
   "productType": "НСО",
   "productCode": 1,
   "registerType": "type",
   "mdmCode": "mdmCode",
   "contractNumber": "N123",
   "contractDate": "2024-07-02",
   "priority": 1
   }

## 3. Создание Продуктового Регистра (ПР) к ЭП - **corporate-settlement-account/create**

   {
   "instanceId": 1,
   "registryTypeCode":"03.012.002_47533_ComSoLd",
   "currencyCode": "800",
   "branchCode": "0202",
   "priorityCode": "00",
   "mdmCode": "15",
   "productId": 1
   }





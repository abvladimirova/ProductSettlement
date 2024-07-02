Проект в рамках обучения T1 "Разработка на Java. Практика (Переобучение ЦФТ, 2-й этап). Поток 2_6"
"Задание №5. Разворачивание сервиса"


1. Создать образ проекта для Docker:
docker build -t product_settlement:v1 .

2. Запустить тестовую БД postgre в Docker:
docker-compose up --detach --build
docker-compose up -d --build

3. Запустить контейнер с приложением:
   docker run -d --name app --network host product-settlement




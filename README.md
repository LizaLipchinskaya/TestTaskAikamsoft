# TestTaskAikamsoft

### 1. Описание проекта
Приложение, предоставляющее сервис работы с данными в БД. Структура данных: покупатели, товары, покупки.

Входные параметры приложения: тип операции, путь к входному файлу, путь к выходному файлу. Файлы должны быть в формате json.

### 2. Стек технологий
+ Java 15
+ PostgreSql
+ Maven
+ Gson
+ Json-simple
+ Lombok
+ JDBC

### 3. Инструкция по сборке
+ Установите maven. Перейдите по ссылке и скачайте zip-файл maven.apache.org/download.cgi.
  Распакуйте архив и добавьте путь к каталогу bin в переменную окружения path.
+ Проверьте правильность установки, командой mvn -v
+ Соберите проект: mvn compile
+ Упакуйте в jar-файл: mvn package

### 4. Примеры запуска программы :
+ java -jar TestTaskAikamsoft-1.0.jar search input.json output.json
+ java -jar TestTaskAikamsoft-1.0.jar stat inputStat.json outputStat.json

### 5. Примеры входных и выходных файлов
Примеры можно найти внутри проекта в папке src/main/resources

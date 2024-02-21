# Проект по автоматизации API тестирования для сервиса Reqres.in

- [Используемый стек](#computer-используемый-стек)
- [Список тестов проекта](#computer-список-тестов-проекта-funpay)
- [Запуск автотестов](#arrow_forward-запуск-автотестов)
- [Сборка в Jenkins](#-Автоматическая-сборка-в-Jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Интеграция с Allure TestOps](#-Allure-TestOps--обзор-результатов-проекта)
- [Интеграция с Jira](#-Jira--выведение-результатов-в-таски)
- [Уведомления в Telegram](#-Telegram--отчётность-при-помощи-бота)


## :computer: Используемый стек

<p align="center">
<img width="8%" title="Java" src="mediaReadme/logo/Java.svg">
<img width="8%" title="Allure Report" src="mediaReadme/logo/Allure_Report.svg">
<img width="8%" title="Allure TestOps" src="mediaReadme/logo/AllureTestOps.svg">
<img width="8%" title="Gradle" src="mediaReadme/logo/Gradle.svg">
<img width="8%" title="JUnit5" src="mediaReadme/logo/JUnit5.svg">
<img width="8%" title="GitHub" src="mediaReadme/logo/GitHub.svg">
<img width="8%" title="Jenkins" src="mediaReadme/logo/Jenkins.svg">
<img width="8%" title="Telegram" src="mediaReadme/logo/Telegram.svg">
<img width="8%" title="Jira" src="mediaReadme/logo/Jira.svg">
<img width="8%" title="Jira" src="mediaReadme/logo/Selenoid.svg">
</p>

- Язык для написания тестов : [Java](https://www.java.com/ru/)
- Фреймворк для модульного тестирования : [Junit5](https://github.com/junit-team/junit5)
- Система автоматической сборки : [Gradle](https://github.com/gradle)
- Удаленный запуск с выбором параметров для тестов реализован при помощи :  [Jenkins](https://www.jenkins.io/)
- Отчеты о пройденных тестах формируются при помощи : [Allure](https://github.com/allure-framework)
- Так же отчеты о тестировании отправляются в мессенджер при помощи <code>Telegram</code> бота
- Реализована интеграция  с [Allure TestOps](https://qameta.io/)
- Реализована интеграция с [Jira Software](https://www.atlassian.com/software/jira)
- Реализация API тестов при помощи <code>RestAssured</code>

## :computer: Список auto-тестов проекта Reqres.in
* Создание нового пользователя
* Обновление пользователя
* Регистрация пользователя
* Удаление пользователя
* Проверка выдачи кода 404

## :arrow_forward: Запуск автотестов

### Команда запуска тестов локально в IDE
* <code>gradle clean test -Dhost=local  </code> : Запуск тестов локально
* <code>gradle clean test -Dhost=remote </code> : Запуск тестов удаленно
  
### Команда запуска тестов в Jenkins
```
gradle clean
${TASK}
-Dbrowser=${BROWSER}
-Dversion=${VERSION}
```
При запуске из <code>Jenkins</code> тест будет выполняться удаленно в браузере при помощи <code>Selenoid</code>


## <img width="7%" style="vertical-align:middle" title="Jenkins" src="mediaReadme/logo/Jenkins.svg"> Автоматическая сборка в Jenkins

Для запуска сборки необходимо перейти в раздел <code>Build with Parameters</code>, выбрать нужные вам параметры в <code>browser,version</code> и нажать кнопку <code>Build</code>.
<p align="center">
<img title="Jenkins Build" src="mediaReadme/screens/JenkinsBuild.png">
</p>
В блоке <code>Build History</code> после завершения сборки в поле с номером сборки появятся значки <code>Allure Report</code> и <code>Allure TestOps</code>, кликнув на них можно перейти в отчетность.

## <img width="7%" style="vertical-align:middle" title="Allure Report" src="mediaReadme/logo/Allure_Report.svg"> Пример Allure-отчета
### Overview

<p align="center">
<img title="Allure Overview" src="mediaReadme/screens/AllureReports.png">
</p>

## <img width="7%" style="vertical-align:middle" title="Allure TestOps" src="mediaReadme/logo/AllureTestOps.svg"> Allure TestOps : обзор результатов проекта

На *Dashboard* в <code>TestOps</code> видна статистика тестов в общем :
* Сколько прошли успешно
* Сколько автоматизированы
* Сколько активны и не нуждаются в доработке
* Динамика успешности прохождения в течении времени

<p align="center">
<img title="Allure TestOps DashBoard" src="mediaReadme/screens/TestOpsDashboard.png">
</p>

### Результат выполнения автотеста

В разделе *Test Results* уже можно детально посмотреть каждый кейс, там будут расписаны шаги и результаты. Так же в этом разделе можно добавить новые кейсы на проверку или оставить комментарии уже существующим для доработки.

<p align="center">
<img title="Test Results in Alure TestOps" src="mediaReadme/screens/TestOpsCases.png">
</p>

## <img width="7%" style="vertical-align:middle" title="Jira" src="mediaReadme/logo/Jira.svg"> Jira : выведение результатов в таски

При помощи интеграции <code>Allure TestOps</code> и <code>Jira</code>, в таску можно отобразить существующие в проекте тест-кейсы и результаты их запуска.

<p align="center">
<img title="Jira Task" src="mediaReadme/screens/JiraTask.png">
</p>

### <img width="7%" style="vertical-align:middle" title="Telegram" src="mediaReadme/logo/Telegram.svg"> Telegram : отчётность при помощи бота

После выполнения тестов созданный в <code>Telegram</code> bot собирает данные с <code>AllureReports</code> и отправляет их сообщение в нужный вам чат.

<p align="center">
<img width="70%" title="Telegram Notifications" src="mediaReadme/screens/TelegramScreen1.png"> 
</p>

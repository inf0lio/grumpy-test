# Grumpy Test
[Grumpy](https://github.com/tonsky/grumpy) is simple blog engine with minimal functionality.

[![Build Status](https://travis-ci.org/inf0lio/grumpy-test.svg?branch=master)](https://travis-ci.org/inf0lio/grumpy-test)

Tests is written on Java using jUnit and [Selenide](https://github.com/codeborne/selenide) as the framework powered by Selenium WebDriver.
They run in parallel in different browsers using [Selenoid](https://github.com/aerokube/selenoid) as implementation of Selenium hub.
As status page is used [Selenoid UI](https://github.com/aerokube/selenoid-ui) as user interface for Selenoid.
For reports is used the [Allure](https://github.com/allure-framework/allure2).

## Getting Started
### Build the application
```
docker-compose -f .stack/docker/docker-compose.yml up -d --build --force-recreate
```

After building you have:
* `http://localhost:8080` - The app
* `http://localhost:8181` - Selenoid UI

### Preparation for testing
Pull specific browser images:
```
docker pull selenoid/vnc:chrome_65.0
docker pull selenoid/vnc:firefox_58.0
docker pull selenoid/vnc:opera_51.0
```

### Running the tests
Execute all tests:
```
mvn clean test
```
Only end-to-end tests:
```
mvn clean test -Pe2e
```
Or only acceptance tests:
```
mvn clean test -Pacceptance
```

### The Allure test report
Serve the report:
```
allure serve
```
Or generate and open it:
```
allure generate
allure open
```





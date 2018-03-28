# Grumpy Test
[Grumpy](https://github.com/tonsky/grumpy) is simple blog engine with minimal functionality.

[![Build Status](https://travis-ci.org/inf0lio/grumpy-test.svg?branch=master)](https://travis-ci.org/inf0lio/grumpy-test)

Tests is written on Java using jUnit and [Selenide](https://github.com/codeborne/selenide) as the wrapper on Selenium WebDriver.
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

## Configuration
### Browsers
In order to add and work with some specific browser you need:

1. Make sure that this version of the browser is in the [list](http://aerokube.com/selenoid/latest/#_browser_image_information).
2. Pull the image with this browser. For example:
```
docker pull selenoid/vnc:chrome_63.0
```
3. Add this browser to Selenoid's config file called ***browsers.json***:
```json
"chrome": {
    "default": "65.0",
    "versions": {
      "65.0": {
        //...,
        //...
      },
      "63.0": {
        "image": "selenoid/vnc:chrome_63.0",
        "port": "4444"
      }
    }
  }
```
4. Rebuild the application.
5. And finally add it to test configs:
```java
    public static LinkedList<String[]> getEnvironments() {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"chrome", "65.0", properties.getProperty("grid.url"), Platform.LINUX.toString()});
        env.add(new String[]{"firefox", "58.0", properties.getProperty("grid.url"), Platform.LINUX.toString()});
        env.add(new String[]{"opera", "51.0", properties.getProperty("grid.url"), Platform.LINUX.toString()});

        // new browser here
        env.add(new String[]{"chrome", "63.0", properties.getProperty("grid.url"), Platform.LINUX.toString()});

        return env;
    }
```






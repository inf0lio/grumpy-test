# Grumpy Test
[![Build Status](https://travis-ci.org/inf0lio/grumpy-test.svg?branch=master)](https://travis-ci.org/inf0lio/grumpy-test)

[Grumpy](https://github.com/tonsky/grumpy) is simple blog engine with minimal functionality which is written on Clojure.
Using UI you can only create and update post. Maybe soon we'll see more functionality but now that's all.


## Getting Started
### Build the application
```
docker-compose -f .stack/docker/docker-compose.yml up -d --build --force-recreate
```
After building you have:
* `http://localhost:8080` - The app
* `http://localhost:8181` - [Selenoid UI](http://aerokube.com/selenoid-ui/latest/)

### Preparation for testing
Pull the specific browser images:
```
docker pull selenoid/vnc:chrome_65.0
docker pull selenoid/vnc:firefox_58.0
docker pull selenoid/vnc:opera_51.0
```

### Running the tests
Execute the all tests:
```
mvn clean test
```
Only the end-to-end tests:
```
mvn clean test -Pe2e
```
Or only the acceptance tests:
```
mvn clean test -Pacceptance
```

### The test report
Serve the [Allure](http://allure.qatools.ru/) report:
```
allure serve
```
Or generate it and open:
```
allure generate
allure open
```





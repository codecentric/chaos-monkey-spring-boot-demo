## Chaos Kotlin (Mediator Kotlin Port)
Media recommender ([Mediator](https://github.com/fletchgqc/mediator))

A demonstration app for [Chaos Monkey for Spring Boot](https://github.com/codecentric/chaos-monkey-spring-boot)

Check out the [requests collection](requests) to play around

### Outgoing requests
This demo project shows how latency is injected in outgoing web requests by assaulting webclient.
The service which is called is [https://httpbin.org/](https://httpbin.org/), which is a simple echo service.

#### Details
Runs with following chaos monkey configuration (see `application.properties` or postman collection request "Actuator CM"):

````json
{
  "chaosMonkeyProperties": {
    "enabled": true,
    "togglePrefix": "chaos.monkey"
  },
  "assaultProperties": {
    "level": 1,
    "deterministic": false,
    "latencyRangeStart": 1500,
    "latencyRangeEnd": 1500,
    "latencyActive": true,
    "exceptionsActive": false,
    "exception": {
      "type": null,
      "arguments": null
    },
    "killApplicationActive": false,
    "memoryActive": false,
    "memoryMillisecondsHoldFilledMemory": 90000,
    "memoryMillisecondsWaitNextIncrease": 1000,
    "memoryFillIncrementFraction": 0.15,
    "memoryFillTargetFraction": 0.25,
    "cpuActive": false,
    "cpuMillisecondsHoldLoad": 90000,
    "cpuLoadTargetFraction": 0.9,
    "runtimeAssaultCronExpression": "OFF"
  },
  "watcherProperties": {
    "controller": false,
    "restController": false,
    "service": false,
    "repository": false,
    "component": false,
    "restTemplate": false,
    "webClient": true,
    "actuatorHealth": false,
    "beans": []
  }
}
````

# Parallel requests
Run 20 requests parallel to a total of 200 requests:

`xargs -I % -P 20 curl "http://localhost:8080/movies" \
< <(printf '%s\n' {1..200})`

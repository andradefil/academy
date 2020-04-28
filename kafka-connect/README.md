# Playground of udemy course about apache kafka connect

## Lesson 1
Goal: 
* Read a file and load the content directly into Kafka
* Run in a connector in *standalone mode* (useful for development)


FILE -> CONNECTOR 1 (schema disabled) -> KAFKA TOPIC

Learning:
* Understand how to configure a connector in stadalone mode
* Get a first feel for Kafka Connect Standalone

## Lesson 2
Goal: 
* Read a file and load the content directly into Kafka
* Run in *distributed mode* on our already set-up Kafka Connect Cluster


FILE -> CONNECTOR 2 (schema enabled) -> KAFKA TOPIC

Learning:
* Understand how to configure a connector in distributed mode
* Get a first feel for Kafka Connect Standalone
* Understand the schema configuration option
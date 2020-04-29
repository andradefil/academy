# Playground course - Apache Kafka Series - Kafka Connect Hands-on Learning

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

## Lesson 3
Goal: 
* Gather data from Twitter in Kafka Connect Distributed Mode

Twitter -> Kafka Connect -> Twitter topic

Learning:
* Gather real data using https://github.com/jcustenborder/kafka-connect-twitter
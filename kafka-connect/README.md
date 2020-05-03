# Playground course - Apache Kafka Series - Kafka Connect Hands-on Learning

# Source Connectors
### Lesson 1
Goal: 
* Read a file and load the content directly into Kafka
* Run in a connector in *standalone mode* (useful for development)


FILE -> CONNECTOR 1 (schema disabled) -> KAFKA TOPIC

Learning:
* Understand how to configure a connector in stadalone mode
* Get a first feel for Kafka Connect Standalone

### Lesson 2
Goal: 
* Read a file and load the content directly into Kafka
* Run in *distributed mode* on our already set-up Kafka Connect Cluster


FILE -> CONNECTOR 2 (schema enabled) -> KAFKA TOPIC

Learning:
* Understand how to configure a connector in distributed mode
* Get a first feel for Kafka Connect Standalone
* Understand the schema configuration option

### Lesson 3
Goal: 
* Gather data from Twitter in Kafka Connect Distributed Mode

Twitter -> Kafka Connect -> Twitter topic

Learning:
* Gather real data using https://github.com/jcustenborder/kafka-connect-twitter

## Sync Connector
### Lesson 1
Goal:
* Start an ElasticSearch instance using Docker
* Sink a topic with multiple partitions to ElasticSearch
* Run in distributed mode with multiple tasks

Kafka Twitter Topic -> ElasticSearchSinkConnector -> ElasticSearch

Learning:
* Learn about the `task.max` parameter
* Understand how Sync Connectors work

### Lesson 2
Goal:
* Start an PostgreSQL instance using Docker
* Run in distributed mode with multiple tasks

Kafka Twitter Topic -> JDBCSinkConnector -> ElasticSearch

Learning:
* Learn about the JDBC Sink Connector
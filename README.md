# Jetty Example

Jetty Example is a simple project based on Jetty.
It's goal is to explain how to use Jetty as an embedded application server.
It provide a suite of tests that starts and stops Jetty and hits the application via HTTP.

## Getting Started

Clone the repository:

```
git clone https://github.com/fracassi-marco/jetty-example
cd jetty-example
```

Run the application:

```
mvn compile && mvn exec:java -Dexec.mainClass="Main"
```

Open browser:

```
http://localhost:8282/list
```

This url should show `empty` text

### Prerequisites

You must have Maven

## Running the tests

```
mvn test
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **[Marco Fracassi](https://github.com/fracassi-marco)**

## Contributors

* **[Roberto Grandi](https://github.com/kamakay)**

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE Version 3 - see the [LICENSE](LICENSE) file for details
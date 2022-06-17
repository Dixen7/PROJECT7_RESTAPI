## Getting Started

This is how to set up the project locally.
To get a local copy up and running follow these simple example steps:

### Prerequisites

Check that you have : 
* Java 8 installed
  ```sh
  java -version
  ```
* Maven 4.0.0 installed
  ```sh
  mvn -v
  ```

### Installation

1. Choose a directory for the java project
   ```sh
   cd /path/to/directory/project
   ```
2. Clone the repo
   ```sh
   git clone [(https://github.com/Dixen7/PROJECT7_RESTAPI.git]
   ```
3. Select the poseiden directory
   ```sh
   cd JavaDA_PROJECT7_RESTAPI\Poseiden-skeleton
   ```
4. Create the database in MySQL.
   ```sh
   CREATE DATABASE DEMO;
   ```
5. Create the database in MySQL.
   ** RUN the script SQL
6. Put in the database the data useful for the connection.
   ```sh
   mvn package
   ```
7. Execute the jar file (lombok gonna create all table we needed for this project)
   ```JS
   java -jar ./target/spring-boot-skeleton-0.0.1-SNAPSHOT.jar
   ```
8. Run the .sql files in JavaDA_PROJECT7_RESTAPI\Poseiden-skeleton\doc.

9. To access the application, open your browser, go to [http://localhost:8080/](http://localhost:8080/)

# Sleep Log App

REST api service for sleep logs

## Setup and Installation

First, clone the repository to your local machine using Git:
git clone https://github.com/ijeremic89/sleep-log.git

Dockerfiles are set up. You will need docker and ports 5432 (Postgres) and 8080 (API).
To run everything, simply execute `docker-compose up`. To build and run, execute `docker-compose up --build`.

## Usage
After starting the application, you can access the REST API endpoints provided by your application at http://localhost:8080.
Also you can check postman collection in postman/sleep-log-api.postman_collection.json

## Tests
To run tests locally run command ./gradlew test

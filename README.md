# parking-management

API-only application that would return the closest carparks to a user with the parking lot availability.

## Installation
cd parking-management
```bash
mvn clean install
```

```bash
docker build -t "parking-mgmt" .
```
```bash
docker-compose -f docker-compose.yml up
```
- It will run postgres then admirer (i.e Client that connects to postgres) and then spring boot app.
- Just note that I have mapped *.sql from sql folder in docker-compose.



## Populate static data & External Api Data
```bash
cd caprparkwego
```
```bash
mvn clean install
```
- Step 1

   Carpark Static information

   This dataset provides detailed information about each carpark. This can be treated as static and is to
be loaded with a task from the CSV file provided in the link above. You can check it into the repository
and do not have to automate the downloading and updating of this data.

  Approach : In this approach parsed the static data & pushed the same into the DB.

```bash
 java -jar caprparkwego/target/caprpark-wego-1.0-SNAPSHOT.jar Load-Static-Data
```

This above command will populate the static data in DB.

- Step 2

  Carpark External API availability (https://data.gov.sg/dataset/carpark-availability)

  Approach : In this approach data fetched from the API according to the current timestamp, invoking this api again will upsert the data (ie update the data according to latest TS).
```bash
 java -jar caprparkwego/target/caprpark-wego-1.0-SNAPSHOT.jar Load-API-Data
```
This above command populate the parking availability data from External API in DB.


## API
Now the API, Postgres is up and running using docker, also the data has been populated using Step 1 & Step 2


API URI
```bash
http://localhost:8080/carparks/nearest?latitude=1&longitude=10&page=1&per_page=2
```
Swagger UI
```bash
http://localhost:8080/swagger-ui.html#/parking-lot-controller/getNearByAvailableParkingSlotsUsingGET
```
Actuator
```bash
http://localhost:8080/actuator/
```
Admirer
```bash
http://localhost:8081
```

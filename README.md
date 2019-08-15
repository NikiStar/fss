fight-status-service
- A service that searches for flights with the given parameter and returns flight information
(departure time, arrival time, flight route, flight number, status e.g. on time or delayed)

Create a network
docker network create eurowings_network

Get the elasticsearch docker image
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.5.1

Get the kibana docker image
docker pull docker.elastic.co/kibana/kibana:6.5.1

Start an elasticsearch container
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "ELASTIC_CONTAINER=true" -e "cluster.name=elasticsearch" --network="eurowings_network" --name eurowings_elasticsearch docker.elastic.co/elasticsearch/elasticsearch:6.5.1

Start a kibana container
docker run -p 5601:5601 --network="eurowings_network" -e "ELASTICSEARCH_URL=http://eurowings_elasticsearch:9200"  --name eurowings_kibana docker.elastic.co/kibana/kibana:6.5.1

To build the app, go to the app folder and follow the steps:
Build jar
./mvnw package

Initial Docker build
docker build -t flight-status-service .

Starting the container
docker run -p 8080:8080 --network="eurowings_network" --name fss flight-status-service

Strat Hipersonic:
java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file.testdb --dbname0.testdb

Sample call:

With CURL:
curl -X POST \
  http://localhost:8080/fss \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c867c003-d160-4e58-b6e2-aedf64f90f3d' \
  -H 'cache-control: no-cache' \
  -d '[
 {
        "number": "12ed",
        "carrier": "eurowings",
        "status": "delayed",
        "scheduleDeviation": "+60"
  }
]'



As HTTP:
POST /fss HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: 22b2921c-398a-41f5-b7f7-6438c5532d2d
[
 {
        "number": "12ed",
        "carrier": "eurowings",
        "status": "delayed",
        "scheduleDeviation": "+60"
  }
]------WebKitFormBoundary7MA4YWxkTrZu0gW--


The elasticsearch/kibana image:
Take an elasticsearch docker image by your choise, e.g:
docker pull nshou/elasticsearch-kibana
docker run -d -p 9200:9200 -p 5601:5601 nshou/elasticsearch-kibana



Running Kibana
docker pull docker.elastic.co/kibana/kibana:6.5.1
docker run -p 5601:5601 --network="eurowings_network" -e "ELASTICSEARCH_URL=http://eurowings_elasticsearch:9200"  --name eurowings_kibana docker.elastic.co/kibana/kibana:6.5.1 
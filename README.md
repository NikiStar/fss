fight-status-service
- A service that searches for flights with the given parameter and returns flight information
(departure time, arrival time, flight route, flight number, status e.g. on time or delayed)

Create a network
docker network create eurowings_network

Build jar
./mvnw package

Initial Docker build
docker build -t flight-status-service .

Starting the container
docker run -p 8080:8080 --network="eurowings_network" --name fss flight-status-service

Strat Hipersonic:
java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file.testdb --dbname0.testdb

Sample call
http://localhost:8090/fss?carrier=lufthansa&flightDate=null&flightNumber=334

The elasticsearch/kibana image:
Take an elasticsearch docker image by your choise, e.g:
docker pull nshou/elasticsearch-kibana
docker run -d -p 9200:9200 -p 5601:5601 nshou/elasticsearch-kibana

docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "ELASTIC_CONTAINER=true" -e "cluster.name=elasticsearch" --network="eurowings_network" --name eurowings_elasticsearch docker.elastic.co/elasticsearch/elasticsearch:6.5.1

Running Kibana
docker pull docker.elastic.co/kibana/kibana:6.5.1
docker run -p 5601:5601 --network="eurowings_network" -e "ELASTICSEARCH_URL=http://eurowings_elasticsearch:9200"  --name eurowings_kibana docker.elastic.co/kibana/kibana:6.5.1


# fight-status-service

*A service that searches for flights with the given parameter and returns flight information
(departure time, arrival time, flight route, flight number, status e.g. on time or delayed)*

  

## 1. Create a network

    docker network create eurowings_network

  

 ## 2. Get the elasticsearch docker image

    docker pull docker.elastic.co/elasticsearch/elasticsearch:6.5.1

  

## 3. Get the kibana docker image

	docker pull docker.elastic.co/kibana/kibana:6.5.1

  

## 4. Start an elasticsearch container
Let's create a network we will assign to all three containers in order they to be able to communicate between eachother:

	docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "ELASTIC_CONTAINER=true" -e "cluster.name=elasticsearch" --network="eurowings_network" --name eurowings_elasticsearch docker.elastic.co/elasticsearch/elasticsearch:6.5.1

  

## 5. Start a kibana container

	docker run -p 5601:5601 --network="eurowings_network" -e "ELASTICSEARCH_URL=http://eurowings_elasticsearch:9200"  --name eurowings_kibana docker.elastic.co/kibana/kibana:6.5.1

  

## 6. To build the app, go to the project folder and follow the steps:

**Build jar**

	mvn package

  

**Initial Docker build**

	docker build -t flight-status-service .

  

## 7. Starting the fss container

	docker run -p 8080:8080 --network="eurowings_network" --name fss flight-status-service


---
Here can be found the REST api documentation. It is available after the app is up and running:
http://localhost:8080/swagger-ui.html#/

In order to use the new app, use a REST tool of your choise e.g. Postman or directly the console calls curl  

*Sample call:*

  

With CURL:
	
	curl -X POST \
	  http://localhost:8080/flightsInfo \
	  -H 'Content-Type: application/json' \
	  -H 'Postman-Token: 003ea5f4-d542-4a4a-9b37-ec111b4da990' \
	  -H 'cache-control: no-cache' \
	  -d '[
	    {
		"number": "12ead",
		"carrier": "eurowings",
		"status": "delayed",
		"scheduleDeviation": "+60"
	    }
	]'

  

  

  

As HTTP:

	POST /flightsInfo HTTP/1.1
	Host: localhost:8080
	Content-Type: application/json
	cache-control: no-cache
	Postman-Token: 62408421-5009-4f5e-b2c1-2d8fd6b3b29e
	[
	    {
		"number": "12ead",
		"carrier": "eurowings",
		"status": "delayed",
		"scheduleDeviation": "+60"
	    }
	]------WebKitFormBoundary7MA4YWxkTrZu0gW--

  

package com.eurowings.fss.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.eurowings.fss.model.Flight;

public interface FlightRepository extends ElasticsearchRepository<Flight, String>{

	List<Flight> findByCarrierAndStatus(String carier, String status);

	List<Flight> findByNumber(String flightNumber);

}

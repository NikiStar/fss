package com.eurowings.fss.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eurowings.fss.model.Flight;
import com.eurowings.fss.repository.FlightRepository;



@RestController
@RequestMapping("/fss")
public class FlightStatusServiceController {

	
	@Autowired
	private FlightRepository repository;

	@PostMapping
	public int persistAFlight(@RequestBody List<Flight> flights) {
		repository.saveAll(flights);
		return flights.size();
	}
	
	@GetMapping
	public List<Flight> searchByCarrier(@RequestParam(value = "carrier", required = false) String carrier,
			@RequestParam(value = "status", required = false) String status) {
		
		return repository.findByCarrierAndStatus(carrier, status);
	}
	
	@GetMapping("{flightNumber}")
	public List<Flight> searchByNumber(@PathVariable String flightNumber) {
		return repository.findByNumber(flightNumber);
	}
	
//	@PostMapping("/all")
//	public Optional<Flight> addFlightData(@PathVariable String flightNumber) {
//		repository.findByNumber(flightNumber)
//		return repository.findByNumber(flightNumber);
//	}

}

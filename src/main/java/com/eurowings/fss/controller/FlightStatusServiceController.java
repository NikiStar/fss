package com.eurowings.fss.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eurowings.fss.model.FlightInfo;
import com.eurowings.fss.repository.FlightInfoRepository;

import io.swagger.annotations.ApiOperation;



/**
 * @author nikolaystariradeff
 *
 */
@RestController
@RequestMapping("/flightsInfo")
public class FlightStatusServiceController {

	
	@Autowired
	private FlightInfoRepository repository;

	@ApiOperation(value = "Persist Fligh(s)Info", response = List.class)
	@PostMapping
	public int saveFlightsInfo(@RequestBody List<FlightInfo> flightsInfo) {
		repository.saveAll(flightsInfo);
		return flightsInfo.size();
	}
	@ApiOperation(value = "Search for a flight info by the given parameter", response = List.class)
	@GetMapping
	public List<FlightInfo> searchByCarrier(@RequestParam(value = "carrier", required = false) String carrier,
			@RequestParam(value = "status", required = false) String status) {
		
		return repository.findByCarrierAndStatus(carrier, status);
	}
	@ApiOperation(value = "Search for a FlightInfo for a flight", response = List.class)
	@GetMapping("/{flightNumber}")
	public List<FlightInfo> searchByNumber(@PathVariable String flightNumber) {
		return repository.findByNumber(flightNumber);
	}
	

}

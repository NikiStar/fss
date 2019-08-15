package com.eurowings.fss.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.eurowings.fss.model.FlightInfo;

/**
 * @author nikolaystariradeff
 *
 */
public interface FlightInfoRepository extends ElasticsearchRepository<FlightInfo, String>{

	List<FlightInfo> findByCarrierAndStatus(String carier, String status);

	List<FlightInfo> findByNumber(String flightNumber);

}

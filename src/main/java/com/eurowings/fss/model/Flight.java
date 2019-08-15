/**
 * 
 */
package com.eurowings.fss.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author nikolaystariradeff
 *
 */
@Document(indexName = "ix_flights", type = "flight", shards = 5)
public class Flight {

	public enum FlightStatus {
		ontime, delayed
	}

	@Id
	private String id;
	
	
	
	public String getId() {
		return id == null ? UUID.randomUUID().toString() : id;
	}

	private String number;
	private String carrier;
	private FlightStatus status;
	private String scheduleDeviation;


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	public String getScheduleDeviation() {
		return scheduleDeviation;
	}

	public void setScheduleDeviation(String scheduleDeviation) {
		this.scheduleDeviation = scheduleDeviation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((scheduleDeviation == null) ? 0 : scheduleDeviation.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (carrier == null) {
			if (other.carrier != null)
				return false;
		} else if (!carrier.equals(other.carrier))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (scheduleDeviation == null) {
			if (other.scheduleDeviation != null)
				return false;
		} else if (!scheduleDeviation.equals(other.scheduleDeviation))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [number=" + number + ", carrier=" + carrier + ", status=" + status + ", scheduleDeviation="
				+ scheduleDeviation + "]";
	}

}

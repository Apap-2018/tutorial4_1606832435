package com.apap.tutorial4.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDB;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDB;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDB.save(flight);
	}

	@Override
	public void deleteFlight(long id) {
		flightDB.deleteById(id);
	}

	@Override
	public void updateFlight(long id, String flightNumber, String origin, String destination, Date time) {
		flightDB.getOne(id).setFlightNumber(flightNumber);
		flightDB.getOne(id).setOrigin(origin);
		flightDB.getOne(id).setDestination(destination);
		flightDB.getOne(id).setTime(time);
	}

	@Override
	public FlightModel getFlightById(long id) {
		// TODO Auto-generated method stub
		return flightDB.getOne(id);
	}


}

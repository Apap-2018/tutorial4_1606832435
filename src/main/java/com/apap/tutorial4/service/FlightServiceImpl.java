package com.apap.tutorial4.service;

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

}

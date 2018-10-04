package com.apap.tutorial4.repository;

import com.apap.tutorial4.model.PilotModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotDB extends JpaRepository<PilotModel, Long> {
	PilotModel findByLicenseNumber(String licenseNumber);
}

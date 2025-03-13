package com.suhruth.incidentimapactanalysis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.suhruth.incidentimapactanalysis.model.Shooters;

@Repository
public interface ShootersRepository extends MongoRepository<Shooters, String>{
	
	List<Shooters> findByIncidentId(String incidentId);

}

package com.suhruth.incidentimapactanalysis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.suhruth.incidentimapactanalysis.model.Victims;

@Repository
public interface VictimsRepository extends MongoRepository<Victims, String> {

	List<Victims> findByIncidentId(String incidentId);

}

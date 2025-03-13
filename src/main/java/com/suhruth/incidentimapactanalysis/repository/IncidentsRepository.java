package com.suhruth.incidentimapactanalysis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.suhruth.incidentimapactanalysis.model.Incidents;

@Repository
public interface IncidentsRepository extends MongoRepository<Incidents, String>{

}

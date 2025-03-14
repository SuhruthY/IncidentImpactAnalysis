package com.suhruth.incidentimapactanalysis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document("school_shooting_shooters")
@Data
public class Shooters {
	
	@Id
	private String id;
	
	@Field("Incident_ID")
	private String incidentId;
	private String Age;

}

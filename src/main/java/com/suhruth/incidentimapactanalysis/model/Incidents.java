package com.suhruth.incidentimapactanalysis.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("school_shooting_incidents")
@Data
public class Incidents {

	@Id
	private String id;
	private String Date;
	private int Victims_Killed;
	private int Victims_Wounded;
	private int Duration_min;
	private String School;
	private String School_Level;
	private String City;
	private String State;
	private String Media_Attention;
}

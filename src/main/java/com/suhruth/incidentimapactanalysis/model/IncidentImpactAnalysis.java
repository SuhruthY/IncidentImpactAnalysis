package com.suhruth.incidentimapactanalysis.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncidentImpactAnalysis {

	private String id;
	private Date date;
	private String schoolName;
	private String schoolType;
	private String location;
	private int duration; 
	private long killed;
	private long wounded;
	private long shooters;
	private long totalVictims;
	private String victimToShooters;
	private double fatalityScore;
	private int mediaScore;
}

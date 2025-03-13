package com.suhruth.incidentimapactanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.suhruth.incidentimapactanalysis.service.IncidentImpactAnalysisService;

@RestController
public class IncidentImpactAnalysisController {
	
	@Autowired
	IncidentImpactAnalysisService service;
	
	@GetMapping("/all/{num}")
	public ResponseEntity<?> getAll(@PathVariable("num") int num){
		return ResponseEntity.ok(service.getAll(num, 30));
	}
	
	@GetMapping("/incidents")
	public ResponseEntity<?> getAllIncidents(){
		return ResponseEntity.ok(service.getAllIncidents());
	}
	
	@GetMapping("/shooters")
	public ResponseEntity<?> getAllShooters(){
		return ResponseEntity.ok(service.getAllShooters());
	}
	
	@GetMapping("/victims")
	public ResponseEntity<?> getAllVictims(){
		return ResponseEntity.ok(service.getAllVictims());
	}
	
	
}

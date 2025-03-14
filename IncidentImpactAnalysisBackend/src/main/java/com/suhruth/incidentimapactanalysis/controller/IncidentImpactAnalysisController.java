package com.suhruth.incidentimapactanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suhruth.incidentimapactanalysis.service.IncidentImpactAnalysisService;

@RestController
public class IncidentImpactAnalysisController {

	@Autowired
	IncidentImpactAnalysisService service;

	@GetMapping("/incident-impact-analysis")
	public ResponseEntity<?> getAll(@RequestParam int page) {
		return ResponseEntity.ok(service.getAll(page, 20));
	}
}

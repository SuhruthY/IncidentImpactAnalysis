package com.suhruth.incidentimapactanalysis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.suhruth.incidentimapactanalysis.model.IncidentImpactAnalysis;
import com.suhruth.incidentimapactanalysis.model.Incidents;
import com.suhruth.incidentimapactanalysis.model.PaginatedResponse;
import com.suhruth.incidentimapactanalysis.model.Shooters;
import com.suhruth.incidentimapactanalysis.model.Victims;
import com.suhruth.incidentimapactanalysis.repository.IncidentsRepository;
import com.suhruth.incidentimapactanalysis.repository.ShootersRepository;
import com.suhruth.incidentimapactanalysis.repository.VictimsRepository;

@Service
public class IncidentImpactAnalysisService {

	@Autowired
	IncidentsRepository incidentsRepo;

	@Autowired
	ShootersRepository shootersRepo;

	@Autowired
	VictimsRepository victimsRepo;

	public PaginatedResponse getAll(int pageNumber, int pageSize) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		// Step 1: Use `Page` to manage pagination
		var page = victimsRepo.findAll(PageRequest.of(pageNumber, pageSize));

		// Step 2: Extract data from the page
		List<IncidentImpactAnalysis> analysisList = page.getContent().stream().map(v -> v.getIncidentId()).distinct()
				.map(i -> {

					long[] counts = victimsRepo.findByIncidentId(i).stream().collect(Collectors.teeing(
							Collectors.filtering(v -> "Fatal".equalsIgnoreCase(v.getInjury()), Collectors.counting()),
							Collectors.filtering(v -> v.getInjury() == null || !"Fatal".equalsIgnoreCase(v.getInjury()),
									Collectors.counting()),
							(killed, wounded) -> new long[] { killed, wounded }));

					long shooters = (long) shootersRepo.findByIncidentId(i).size();

					Incidents incident = incidentsRepo.findById(i).orElse(null);
					if (incident == null)
						return null;

					try {
						return IncidentImpactAnalysis.builder().id(i)//
								.date(dateFormat.parse(incident.getDate())) //
								.schoolName(incident.getSchool()) //
								.schoolType(incident.getSchool_Level()) //
								.location(incident.getCity() + ", " + incident.getState()) //
								.killed(counts[0]) //
								.duration(incident.getDuration_min()) //
								.wounded(counts[1]) //
								.shooters(shooters) //
								.totalVictims(counts[0] + counts[1])//
								.victimToShooters((counts[0] + counts[1]) + "/" + shooters) //
								.fatalityScore(counts[0] * 100 / (counts[0] + counts[1]))//
								.mediaScore(
										switch (incident.getMedia_Attention() != null ? incident.getMedia_Attention()
												: "") {
										case "National" -> 5;
										case "International" -> 10;
										case "Regional" -> 3;
										case "Local" -> 1;
										default -> 0;
										})
								.build();
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				}).collect(Collectors.toList());

		// Step 3: Return the custom `PaginatedResponse`
		return PaginatedResponse.builder() //
				.content(analysisList)//
				.currentPage(page.getNumber())//
				.totalPages(page.getTotalPages() - 1) //
				.totalElements(page.getTotalElements()).build();
	}

	public List<Incidents> getAllIncidents() {
		return incidentsRepo.findAll();
	}

	public List<Shooters> getAllShooters() {
		return shootersRepo.findAll();
	}

	public List<Victims> getAllVictims() {
		return victimsRepo.findAll();
	}

}

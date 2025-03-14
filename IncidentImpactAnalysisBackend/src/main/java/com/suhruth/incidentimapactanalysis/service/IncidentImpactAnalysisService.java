package com.suhruth.incidentimapactanalysis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suhruth.incidentimapactanalysis.exception.PageLimitExceededException;
import com.suhruth.incidentimapactanalysis.model.IncidentImpactAnalysis;
import com.suhruth.incidentimapactanalysis.model.Incidents;
import com.suhruth.incidentimapactanalysis.model.PaginatedResponse;
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
		
		List<Victims> allVictims = victimsRepo.findAll();

		// Step 1: Extract distinct incident IDs and count them
		List<String> distinctIncidentIds = allVictims.stream()
		        .map(Victims::getIncidentId)
		        .distinct()
		        .collect(Collectors.toList()); // Collect distinct IDs first

		// Step 2: Get the total count
		int totalDistinctIncidents = distinctIncidentIds.size();

		// Step 3: Apply pagination logic
		distinctIncidentIds = distinctIncidentIds.stream()
		        .skip((long) pageNumber * pageSize)
		        .limit(pageSize)
		        .collect(Collectors.toList());


		if (distinctIncidentIds.isEmpty()) {
			throw new PageLimitExceededException("No more content available. Page limit reached.");
		}

		// Step 4: Extract data from the page
		List<IncidentImpactAnalysis> analysisList = distinctIncidentIds.parallelStream() //
				.map(i -> {

					Incidents incident = incidentsRepo.findById(i).orElse(null);
					if (incident == null)
						return null;

					// Aggregate victim data efficiently
					long[] counts = allVictims.stream().filter(v -> v.getIncidentId().equals(i))
							.collect(Collectors.teeing(
									Collectors.filtering(v -> "Fatal".equalsIgnoreCase(v.getInjury()),
											Collectors.counting()),
									Collectors.filtering(
											v -> v.getInjury() == null || !"Fatal".equalsIgnoreCase(v.getInjury()),
											Collectors.counting()),
									(killed, wounded) -> new long[] { killed, wounded }));

					long shooters = shootersRepo.findAll().stream().filter(s -> s.getIncidentId().equals(i)).count();

					try {
						return IncidentImpactAnalysis.builder() //
								.id(i) //
								.date(dateFormat.parse(incident.getDate())) //
								.schoolName(incident.getSchool()) //
								.schoolType(incident.getSchool_Level()) //
								.location(incident.getCity() + ", " + incident.getState()) //
								.killed(counts[0]) //
								.duration(incident.getDuration_min()) //
								.wounded(counts[1]) //
								.shooters(shooters) //
								.totalVictims(counts[0] + counts[1]) //
								.victimToShooters((counts[0] + counts[1]) + "/" + shooters) //
								.fatalityScore(counts[0] * 100 / (counts[0] + counts[1])) //
								.mediaScore(mapMediaScore(incident.getMedia_Attention())) //
								.build();
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				}).collect(Collectors.toList());

		// Step 5: Return the custom `PaginatedResponse`
		int totalPages = (int) Math.ceil((double) totalDistinctIncidents / pageSize)-1;

		return PaginatedResponse.builder() //
				.content(analysisList) //
				.currentPage(pageNumber) //
				.totalPages(totalPages) //
				.totalElements(totalDistinctIncidents) //
				.build();
	}

	// Optimized media score mapping
	private int mapMediaScore(String mediaAttention) {
		return switch (mediaAttention != null ? mediaAttention : "") {
		case "National" -> 5;
		case "International" -> 10;
		case "Regional" -> 3;
		case "Local" -> 1;
		default -> 0;
		};
	}
}

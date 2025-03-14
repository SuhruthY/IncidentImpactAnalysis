package com.suhruth.incidentimapactanalysis.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginatedResponse {
	private List<IncidentImpactAnalysis> content;
	private int currentPage;
	private int totalPages;
	private long totalElements;

}
export interface IncidentImpactAnalysis {
    id: string;
    date: Date;
    schoolName: string;
    schoolType: string;
    location: string;
    duration: number;
    totalVictims: number;
    victimToShooters: string;
    fatalityScore: number;
    mediaScore: number;
}

export interface ApiResponse {
    content: IncidentImpactAnalysis[];
    currentPage: number;
    totalElements: number;
    totalPages: number;
}

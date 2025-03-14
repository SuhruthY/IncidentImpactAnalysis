# Incident Impact Analysis

Incident Impact Analysis is a full-stack web application designed to analyze and manage incidents based on their severity. The project is split into two parts:

1. **IncidentImpactAnalysisBackend** (Spring Boot)
2. **IncidentImpactAnalysisFrontend** (Angular)

## Features
- Analyze incidents and assess their impact.
- Display a list of incidents with descriptions and severity.
- RESTful API integration for data exchange between the backend and frontend.

---

## Directory Structure
```
IncidentImpactAnalysis/
├── IncidentImpactAnalysisBackend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/suhruth/incidentimapactanalysis/
│   │   │   │   ├── controller/IncidentImpactAnalysisController.java
│   │   │   │   ├── model/Incident.java
│   │   │   │   ├── service/IncidentService.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   ├── pom.xml
├── IncidentImpactAnalysisFrontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/incident-list/
│   │   │   │   ├── incident-list.component.html
│   │   │   │   ├── incident-list.component.ts
│   │   │   │   ├── incident-list.component.css
│   │   ├── services/incident.service.ts
│   │   ├── app.module.ts
│   │   ├── app.component.ts
│   ├── angular.json
```

---

## Prerequisites
Ensure you have the following installed:
- Java 8 or higher
- Maven 3.6+
- Node.js & npm (for Angular frontend)
- Angular CLI

---

## Backend Setup (Spring Boot)
1. Navigate to the backend folder:
   ```bash
   cd IncidentImpactAnalysisBackend
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
4. The API will be accessible at `http://localhost:8080/incident-impact-analysis?page=PAGE_NUM`

---

## Frontend Setup (Angular)
1. Navigate to the frontend folder:
   ```bash
   cd IncidentImpactAnalysisFrontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Run the Angular development server:
   ```bash
   ng serve
   ```
4. The application will be accessible at `http://localhost:4200`

---

## API Endpoints
### Backend API
- **`GET /incident-impact-analysis`** - Analyzes and returns the incident impact.

### Frontend Integration
- Uses `IncidentService` to fetch incident data from the backend.

---

## Future Enhancements
- Implement database integration to persist incident data.
- Add detailed impact analysis logic.
- Introduce role-based authentication for secure access.

---

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

---

## License
This project is licensed under the [MIT License](LICENSE).

---

## Author
**Suhruth Yambakam**  
[GitHub Profile](https://github.com/SuhruthY)


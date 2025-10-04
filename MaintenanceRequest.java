package first;

import java.time.LocalDate;

public class MaintenanceRequest {
private int requestId;
private Unit unit;
private Tenant tenant;
private String description;
private LocalDate dateReported;
private String status;

public MaintenanceRequest(int requestId, Unit unit, Tenant tenant, String description, LocalDate dateReported) {
this.requestId = requestId;
this.unit = unit;
this.tenant = tenant;
this.description = description;
this.dateReported = dateReported;
this.status = "Open";
}

public void setStatus(String status) {
this.status = status;
}

public String getRequestInfo() {
return "Request #" + requestId + " (" + status + "): " + description;
}

}

package first;
import java.util.ArrayList;

public class Tenant {
private int tenantId;
private String name;
private String contactInfo;
private ArrayList<Lease> leases;
public Tenant(int tenantId, String name, String contactInfo) {
this.tenantId = tenantId;
this.name = name;
this.contactInfo = contactInfo;
this.leases = new ArrayList<>();
}
public void addLease(Lease lease) {
leases.add(lease);
}
public ArrayList<Lease> getLeases() {
return leases;
}
public void requestMaintenance(Unit unit, String issue) {
}
}

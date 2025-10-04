package first;
import java.time.LocalDate;
import java.util.ArrayList;

public class Lease {
private int leaseId;
private Unit unit;
private Tenant tenant;
private double rentAmount;
private LocalDate startDate;
private LocalDate endDate;
private ArrayList<RentInvoice> invoices;

public Lease(int leaseId, Unit unit, Tenant tenant, double rentAmount, LocalDate startDate, LocalDate endDate) {
this.leaseId = leaseId;
this.unit = unit;
this.tenant = tenant;
this.rentAmount = rentAmount;
this.startDate = startDate;
this.endDate = endDate;
this.invoices = new ArrayList<>();
}

public void activate() {
unit.assignLease(this);
}

public void terminate() {
unit.vacate();
}

public RentInvoice generateInvoice() {
RentInvoice invoice = new RentInvoice(invoices.size() + 1, this, LocalDate.now(), rentAmount);
invoices.add(invoice);
return invoice;
}

public double getOutstandingDues() {
double totalDue = 0;
for (RentInvoice invoice : invoices) {
if (!invoice.isPaid()) totalDue += invoice.getTotal();
}
return totalDue;
}

}

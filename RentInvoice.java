package first;

import java.time.LocalDate;
import java.util.ArrayList;

public class RentInvoice {
private int invoiceId;
private Lease lease;
private LocalDate invoiceDate;
private double rentAmount;
private double penalties;
private boolean paid;
private ArrayList<Payment> payments;

public RentInvoice(int invoiceId, Lease lease, LocalDate invoiceDate, double rentAmount) {
this.invoiceId = invoiceId;
this.lease = lease;
this.invoiceDate = invoiceDate;
this.rentAmount = rentAmount;
this.penalties = 0.0;
this.paid = false;
this.payments = new ArrayList<>();
}

public void addPenalty(double amount) {
this.penalties += amount;
}

public void addPayment(Payment payment) {
payments.add(payment);
checkPaymentStatus();
}

public boolean isPaid() {
return paid;
}

public double getTotal() {
return rentAmount + penalties;
}

private void checkPaymentStatus() {
double sum = 0;
for (Payment p : payments) sum += p.getAmount();
paid = sum >= getTotal();
}

}

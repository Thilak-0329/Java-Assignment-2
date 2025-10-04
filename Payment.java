package first;

import java.time.LocalDate;

public class Payment {
private int paymentId;
private RentInvoice invoice;
private LocalDate paymentDate;
private double amount;

public Payment(int paymentId, RentInvoice invoice, LocalDate paymentDate, double amount) {
this.paymentId = paymentId;
this.invoice = invoice;
this.paymentDate = paymentDate;
this.amount = amount;
}

public void process() {
invoice.addPayment(this);
}

public double getAmount() {
return amount;
}

}

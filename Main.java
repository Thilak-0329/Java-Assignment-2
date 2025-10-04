package first;
import java.util.*;
import java.time.LocalDate;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Property> properties = new ArrayList<>();
    private static ArrayList<Tenant> tenants = new ArrayList<>();
    private static ArrayList<MaintenanceRequest> requests = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("---- Real Estate Leasing & Rent Collection ----");
            System.out.println("1. Add Property");
            System.out.println("2. Add Unit to Property");
            System.out.println("3. Add Tenant");
            System.out.println("4. Create Lease");
            System.out.println("5. Generate Invoice");
            System.out.println("6. Record Payment");
            System.out.println("7. Log Maintenance Request");
            System.out.println("8. Display Units");
            System.out.println("9. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // eat newline

            switch (choice) {
                case 1:
                    addProperty();
                    break;
                case 2:
                    addUnit();
                    break;
                case 3:
                    addTenant();
                    break;
                case 4:
                    createLease();
                    break;
                case 5:
                    generateInvoice();
                    break;
                case 6:
                    recordPayment();
                    break;
                case 7:
                    logMaintenance();
                    break;
                case 8:
                    displayUnits();
                    break;
                case 9:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addProperty() {
        System.out.print("Property name: ");
        String name = scanner.nextLine();
        System.out.print("Property address: ");
        String address = scanner.nextLine();
        Property p = new Property(properties.size() + 1, name, address);
        properties.add(p);
        System.out.println("Property added.");
    }

    private static void addUnit() {
        if (properties.isEmpty()) {
            System.out.println("Add a property first.");
            return;
        }
        System.out.print("Property ID: ");
        int propId = scanner.nextInt();
        scanner.nextLine();
        if (propId < 1 || propId > properties.size()) {
            System.out.println("Invalid Property ID.");
            return;
        }
        Property prop = properties.get(propId - 1);
        System.out.print("Unit number: ");
        String unitNum = scanner.nextLine();
        if(unitNum == null || unitNum.isEmpty()) {
            System.out.println("Unit number cannot be empty.");
            return;
        }
        Unit u = new Unit(prop.getUnits().size() + 1, unitNum, prop);
        prop.addUnit(u);
        System.out.println("Unit added.");
    }

    private static void addTenant() {
        System.out.print("Tenant name: ");
        String name = scanner.nextLine();
        System.out.print("Contact info: ");
        String contact = scanner.nextLine();
        Tenant t = new Tenant(tenants.size() + 1, name, contact);
        tenants.add(t);
        System.out.println("Tenant added.");
    }

    private static void createLease() {
        if (properties.isEmpty() || tenants.isEmpty()) {
            System.out.println("Add properties, units, and tenants first.");
            return;
        }

        System.out.print("Enter property ID: ");
        int propertyId = scanner.nextInt();
        scanner.nextLine();
        if (propertyId < 1 || propertyId > properties.size()) {
            System.out.println("Invalid Property ID.");
            return;
        }
        Property property = properties.get(propertyId - 1);

        System.out.print("Enter unit number: ");
        String unitNumber = scanner.nextLine();
        Unit leaseUnit = null;
        for (Unit u : property.getUnits()) {
            String un = u.getUnitNumber();
            if (un != null && un.equalsIgnoreCase(unitNumber) && u.isVacant()) {
                leaseUnit = u;
                break;
            }
        }
        if (leaseUnit == null) {
            System.out.println("Unit not found or not vacant.");
            return;
        }

        System.out.print("Enter tenant ID: ");
        int tenantId = scanner.nextInt();
        scanner.nextLine();
        if (tenantId < 1 || tenantId > tenants.size()) {
            System.out.println("Invalid tenant ID.");
            return;
        }
        Tenant tenant = tenants.get(tenantId - 1);

        System.out.print("Enter rent amount: ");
        double rent = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter lease start date (yyyy-mm-dd): ");
        String startStr = scanner.nextLine();

        System.out.print("Enter lease end date (yyyy-mm-dd): ");
        String endStr = scanner.nextLine();

        Lease newLease = new Lease(1, leaseUnit, tenant, rent,
                LocalDate.parse(startStr),
                LocalDate.parse(endStr));
        newLease.activate();
        tenant.addLease(newLease);
        System.out.println("Lease created and activated.");
    }

    private static void generateInvoice() {
        System.out.print("Enter tenant ID: ");
        int tenantId = scanner.nextInt();
        scanner.nextLine();
        if (tenantId < 1 || tenantId > tenants.size()) {
            System.out.println("Tenant not found.");
            return;
        }
        Tenant tenant = tenants.get(tenantId - 1);

        if (tenant.getLeases().isEmpty()) {
            System.out.println("No leases for this tenant.");
            return;
        }
        Lease lease = tenant.getLeases().get(0); // Simplification
        RentInvoice inv = lease.generateInvoice();
        System.out.println("Invoice generated: Amount = " + inv.getTotal());
    }

    private static void recordPayment() {
        System.out.print("Enter tenant ID: ");
        int tenantId = scanner.nextInt();
        scanner.nextLine();
        if (tenantId < 1 || tenantId > tenants.size()) {
            System.out.println("Tenant not found.");
            return;
        }
        Tenant tenant = tenants.get(tenantId - 1);

        if (tenant.getLeases().isEmpty()) {
            System.out.println("No leases found.");
            return;
        }
        Lease lease = tenant.getLeases().get(0);
        if (lease == null || lease.invoices.isEmpty()) {
            System.out.println("No invoices to pay.");
            return;
        }
        RentInvoice inv = lease.invoices.get(0);

        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter payment date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();

        Payment pay = new Payment(1, inv, LocalDate.parse(dateStr), amount);
        pay.process();
        System.out.println("Payment recorded. Paid? " + inv.isPaid());
    }

    private static void logMaintenance() {
        System.out.print("Enter property ID: ");
        int propertyId = scanner.nextInt();
        scanner.nextLine();
        if (propertyId < 1 || propertyId > properties.size()) {
            System.out.println("Invalid Property ID.");
            return;
        }
        Property property = properties.get(propertyId - 1);

        System.out.print("Enter unit number: ");
        String unitNumber = scanner.nextLine();
        Unit unit = null;
        for (Unit u : property.getUnits()) {
            String un = u.getUnitNumber();
            if (un != null && un.equalsIgnoreCase(unitNumber)) {
                unit = u;
                break;
            }
        }
        if (unit == null) {
            System.out.println("Unit not found.");
            return;
        }

        System.out.print("Enter tenant ID: ");
        int tenantId = scanner.nextInt();
        scanner.nextLine();
        if (tenantId < 1 || tenantId > tenants.size()) {
            System.out.println("Invalid Tenant ID.");
            return;
        }
        Tenant tenant = tenants.get(tenantId - 1);

        System.out.print("Describe issue: ");
        String desc = scanner.nextLine();
        System.out.print("Date reported (yyyy-mm-dd): ");
        String dateReported = scanner.nextLine();

        MaintenanceRequest req = new MaintenanceRequest(requests.size() + 1, unit, tenant, desc, LocalDate.parse(dateReported));
        requests.add(req);
        System.out.println("Request logged: " + req.getRequestInfo());
    }

    private static void displayUnits() {
        for (Property p : properties) {
            System.out.println("\nProperty: " + p.getUnits());
            for (Unit u : p.getUnits()) {
                String un = u.getUnitNumber();
                System.out.print("Unit " + (un == null ? "?" : un) + ": ");
                System.out.println(u.isVacant() ? "Vacant" : "Leased");
            }
        }
    }
}

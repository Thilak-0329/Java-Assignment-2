package first;

public class Unit {
    private int unitId;
    private String unitNumber;
    private boolean vacant = true;
    private Property property;
    private Lease lease;

    public Unit(int unitId, String unitNumber, Property property) {
        this.unitId = unitId;
        this.unitNumber = unitNumber;
        this.property = property;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void assignLease(Lease lease) {
        this.lease = lease;
        this.vacant = false;
    }

    public void vacate() {
        this.lease = null;
        this.vacant = true;
    }

    public Lease getLease() {
        return lease;
    }

    public int getUnitId() {
        return unitId;
    }

	public int getUnitId1() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isVacant1() {
		// TODO Auto-generated method stub
		return false;
	}

	public void assignLease1(Lease lease2) {
		// TODO Auto-generated method stub
		
	}

	public String getUnitNumber() {
		return unitNumber; // <-- CORRECTION HERE
	}

    // Getters and Setters omitted for brevity
}

package first;
import java.util.ArrayList;

public class Property {
private int propertyId;
private String name;
private String address;
private ArrayList<Unit> units;
public Property(int propertyId, String name, String address) {
this.propertyId = propertyId;
this.name = name;
this.address = address;
this.units = new ArrayList<>();
}
public void addUnit(Unit unit) {
units.add(unit);
}
public void removeUnit(int unitId) {
units.removeIf(u -> u.getUnitId() == unitId);
}
public ArrayList<Unit> getUnits() {
return units;
}
public ArrayList<Unit> getVacantUnits() {
ArrayList<Unit> vacant = new ArrayList<>();
for (Unit u : units) {
if (u.isVacant()) {
vacant.add(u);
}
}
return vacant;
}
}

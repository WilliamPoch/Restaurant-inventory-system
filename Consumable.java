package restaurantInventorySystem;

public class Consumable extends Item {
	private final String type = "Consumable";
	
	public Consumable(String name, int quantity, String um, double unitPrice) {
		super(name, quantity, um, unitPrice);
	}
	
	public Consumable(String name, int quantity, double unitPrice) {
		super(name, quantity, unitPrice);
	}
	
	@Override
	String getType() {
		return this.type;
	}
	
}

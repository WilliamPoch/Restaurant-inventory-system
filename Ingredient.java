package restaurantInventorySystem;

public class Ingredient extends Item {
	private final String type = "Ingredient";
	
	public Ingredient(String name, int quantity, String um, double unitPrice) {
		super(name, quantity, um, unitPrice);
	}
	
	public Ingredient(String name, int quantity, double unitPrice) {
		super(name, quantity, unitPrice);
	}
	
	@Override
	String getType() {
		return this.type;
	}
	
}

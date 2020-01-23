package restaurantInventorySystem;

public class AddConsumable implements AddItem{

	@Override
	public Item addItem(String name, int quantity, String um, double unitPrice) {
		Item item = new Consumable(name, quantity, um, unitPrice);
		return item;
	}
	
	public Item addItemb(String name, int quantity, double unitPrice) {
		Item item = new Ingredient(name, quantity, unitPrice);
		return item;
	}

}

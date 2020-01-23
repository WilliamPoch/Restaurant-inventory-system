package restaurantInventorySystem;


public class InventoryManager {
	private AddItem add;
	private AddItem ingredient = new AddIngredient();
	private AddItem consumable = new AddConsumable();
	
	public InventoryManager() {
		this.add = ingredient;
	}
	
	public void setIngredient() {
		this.add = ingredient;
	}
	
	public void setConsumable() {
		this.add = consumable;
	}
	
	public Item item(String name, int quantity, String um, double price) {
		return this.add.addItem(name, quantity, um,  price);
	}
	
	public Item itemb(String name, int quantity, double price) {
		return this.add.addItemb(name, quantity, price);
	}
	
	public void addItem(String name, int quantity, String unitofMeasurement, double price) {
		DatabaseHandler.getInstance().addItem(add.addItem(name, quantity, unitofMeasurement, price));
	}
	
	public void addItemb(String name, int quantity, double price) {
		DatabaseHandler.getInstance().addItem(add.addItemb(name, quantity, price));
	}
	
	public boolean restock(int quantity, String restockItem) {
		return DatabaseHandler.getInstance().restockItem(quantity, restockItem);
	}
	
	public boolean updateItem(Item item, String updateName) {
		return DatabaseHandler.getInstance().updateItem(item, updateName);
	}
	
	public String getItemType(String name) {
		return DatabaseHandler.getInstance().getItemType(name);
	}
	
	public String showAllItems() {
		return DatabaseHandler.getInstance().viewItems();
	}
	
	public String search(String name) {
		return DatabaseHandler.getInstance().queryItem(name);
	}
	
	public boolean delete(String name) {
		return DatabaseHandler.getInstance().deleteItem(name);
	}
	
	public void disconnect() {
		DatabaseHandler.getInstance().conClose();
	}
}



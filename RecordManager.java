package restaurantInventorySystem;

public class RecordManager {
	
	public boolean recordUsage(int quantity, String name) {
		return DatabaseHandler.getInstance().useItem(name, quantity);
	}
	
	public String monthlyExpense() {
		return DatabaseHandler.getInstance().monthlyExpense();
	}
	
	public String monthlyUsage() {
		return DatabaseHandler.getInstance().getMonthlyUsage();
	}
	
}

package restaurantInventorySystem;

 abstract public class Item {
	private String name;
	private int quantityInStock; // Based of unit of measurement.
	private String unitOfMeasurement;
	private double unitPrice; // Based on unit of measurement. ex: per kg, litre, unit.
	private double inventoryValue; // quantity in stock * unit price
	
	public Item(String name, int quantity, String um, double unitPrice) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantityInStock = quantity;
		this.unitOfMeasurement = um;
	}
	
	public Item(String name, int quantity, double price) {
		this.name = name;
		this.unitPrice = price;
		this.quantityInStock = quantity;
	}
	
	abstract String getType();
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQiS() {
		return this.quantityInStock;
	}

	public void setQiS(int qis) {
		this.quantityInStock = qis;
		setInventoryValue();
	}

	public String getUM() {
		return this.unitOfMeasurement;
	}

	public void setUM(String UoM) {
		this.unitOfMeasurement = UoM;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double uPrice) {
		this.unitPrice = uPrice;
		setInventoryValue();
	}
	
	public void setInventoryValue() {
		this.inventoryValue = this.quantityInStock * this.unitPrice;
	}
	
	public double getInventoryValue() {
		setInventoryValue();
		return this.inventoryValue;
	}

	
}

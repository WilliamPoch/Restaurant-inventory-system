package restaurantInventorySystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;



public class DatabaseHandler {
	private static DatabaseHandler DatabaseHandler;
	private Connection con;

	private DatabaseHandler() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant_inventory", "root", "");	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static DatabaseHandler getInstance() {
		if (DatabaseHandler == null) {
			DatabaseHandler = new DatabaseHandler();
		}
		return DatabaseHandler;
	}
	
	public boolean addItem(Item item) { 
		try {
			String type = item.getType();
			String name = item.getName();
			int quantity = item.getQiS();
			String UoM = item.getUM();
			double unitPrice = item.getUnitPrice();
			double invValue = item.getInventoryValue();
			
			PreparedStatement st = con.prepareStatement("INSERT into inventory"
					+ "(Type, Name, Quantity, UnitOfMeasure, UnitPrice, InventoryValue) "
					+ "VALUES (?, ?, ?, ?, ? ,? )");
			
			st.setString(1, type);
			st.setString(2, name);
			st.setInt(3, quantity);
			st.setString(4, UoM);
			st.setDouble(5, unitPrice);
			st.setDouble(6, invValue);
			st.executeUpdate();

			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deleteItem(String name) {
		try {
			PreparedStatement st = con.prepareStatement("DELETE from inventory WHERE Name = ? ");
			st.setString(1, name);
			st.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean restockItem(int quantity, String restockItem) {
		try {			
			java.util.Date date = new Date();
			Object param = new java.sql.Timestamp(date.getTime());
			PreparedStatement st = con.prepareStatement("UPDATE inventory "
					+ "SET Quantity = Quantity + ? , RestockDate = ? "
					+ "WHERE Name = ? ");
			st.setInt(1, quantity);
			st.setObject(2, param);
			st.setString(3, restockItem);
			st.executeUpdate();
			st.close();
			PreparedStatement stmt = con.prepareStatement("SELECT Quantity, UnitPrice from inventory WHERE Name = ? ");
			stmt.setString(1, restockItem);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			double invalue = rs.getInt(1) * rs.getDouble(2);
			stmt.close();
			PreparedStatement stt = con.prepareStatement("UPDATE inventory "
					+ "SET InventoryValue = ? "
					+ "WHERE Name = ? ");
			stt.setDouble(1, invalue);
			stt.setString(2, restockItem);
			stt.executeUpdate();
			stt.close();
			
			
			return true;
		} catch (Exception e) {
			System.out.println("Restock failed: "+ e);
		}
		return false;
	}
	
	public boolean updateItem(Item item, String updateName) {
		try {
			String name = item.getName();
			int quantity = item.getQiS();
			String um = item.getUM();
			double unitPrice = item.getUnitPrice();
			double invValue = item.getInventoryValue();
			
			PreparedStatement st = con.prepareStatement("UPDATE inventory "
					+ "SET Name = ?, Quantity = ?, UnitOfMeasure = ?, UnitPrice = ?, InventoryValue = ? "
					+ "WHERE Name = ?");
			st.setString(1, name);
			st.setInt(2, quantity);
			st.setString(3, um);
			st.setDouble(4, unitPrice);
			st.setDouble(5, invValue);
			st.setString(6, updateName);
			st.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean useItem(String name, int quantity) {
		try {			
			PreparedStatement st = con.prepareStatement("UPDATE inventory "
					+ "SET Quantity = Quantity - ? "
					+ "WHERE Name = ? ");
			st.setInt(1, quantity);
			st.setString(2, name);
			st.executeUpdate();
			st.close();
			PreparedStatement stmt = con.prepareStatement("SELECT Quantity, UnitPrice from inventory WHERE Name = ? ");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			double invalue = rs.getInt(1) * rs.getDouble(2);
			stmt.close();
			PreparedStatement stt = con.prepareStatement("UPDATE inventory "
					+ "SET InventoryValue = ? "
					+ "WHERE Name = ? ");
			stt.setDouble(1, invalue);
			stt.setString(2, name);
			stt.executeUpdate();
			stt.close();
			PreparedStatement stm = con.prepareStatement("INSERT into usagehistory (usageAmount, RestockDate, InventoryID) SELECT ? , RestockDate, ID FROM inventory where Name = ? ");
			stm.setString(2, name);
			stm.setInt(1, quantity);
			stm.executeUpdate();
			stm.close();

			
			return true;
		} catch (Exception e) {
			System.out.println("Restock failed: "+ e);
		}
		return false;
	}
	
	public String viewItems() {
		try {
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * from inventory");
			StringBuilder str = new StringBuilder();
			str.append("Type");
			str.append("\t");
			str.append("Name");
			str.append("\t");
			str.append("Quantity");
			str.append("\t");
			str.append("UoM");
			str.append("\t");
			str.append("Price");
			str.append("\t");
			str.append("Inventory Value");
			str.append("\t");
			str.append("Restock Date");
			str.append("\n");
			while (result.next()) {
				str.append(result.getString(2));
				str.append("\t");
				str.append(result.getString(3));
				str.append("\t");
				str.append(result.getInt(4));
				str.append("\t");
				str.append(result.getString(5));
				str.append("\t");
				str.append(result.getDouble(6));
				str.append("\t");
				str.append(result.getDouble(7));
				str.append("\t");
				str.append(result.getTimestamp(8));
				str.append("\n");
			}
			
			return str.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "N/A";
	}
	
	public String getMonthlyUsage() {
		try {
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery(
					"SELECT Name, UnitPrice, usagehistory.usageAmount, UnitOfMeasure, inventory.RestockDate, usagehistory.UsageDate from inventory INNER JOIN usagehistory on inventory.ID = usagehistory.InventoryID WHERE (SELECT MONTH(UsageDate)) = (SELECT MONTH(CURDATE()))");
			StringBuilder str = new StringBuilder();
			double total = 0.0;
			str.append("Name");
			str.append("\t");
			str.append("Unit Price");
			str.append("\t");
			str.append("Quantity");
			str.append("\t");
			str.append("UoM");
			str.append("\t");
			str.append("Restock Date");
			str.append("\t");
			str.append("Usage Date");
			str.append("\n");
			while (result.next()) {
				str.append(result.getString(1));
				str.append("\t");
				str.append(result.getDouble(2));
				str.append("\t");
				str.append(result.getInt(3));
				str.append("\t");
				str.append(result.getString(4));
				str.append("\t");
				str.append(result.getTimestamp(5));
				str.append("\t");
				str.append(result.getTimestamp(6));
				str.append("\n");
				total += result.getDouble(3) * result.getDouble(2);
			}
			str.append("Total Cost: " + total);
			return str.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "N/A";
	}
	
	public String getItemType(String name) {
		try {
			PreparedStatement st = con.prepareStatement("SELECT * from inventory WHERE Name = ? ");
			st.setString(1, name);
			ResultSet result = st.executeQuery();
			String type = "";
			while (result.next()) {
				type = result.getString(1);
			}
			return type;
		} catch (Exception e) {
			System.out.println(e);
		}
	return "N/A";
	}
	public String queryItem(String query) {
		try {
			
			PreparedStatement st = con.prepareStatement("SELECT * from inventory WHERE Name = ? ");
			st.setString(1, query);
			ResultSet result = st.executeQuery();
			StringBuilder str = new StringBuilder();
			str.append("Type");
			str.append("\t");
			str.append("Name");
			str.append("\t");
			str.append("Quantity");
			str.append("\t");
			str.append("UoM");
			str.append("\t");
			str.append("Price");
			str.append("\t");
			str.append("Inventory Value");
			str.append("\t");
			str.append("Restock Date");
			str.append("\n");
			while (result.next()) {
				str.append(result.getString(2));
				str.append("\t");
				str.append(result.getString(3));
				str.append("\t");
				str.append(result.getInt(4));
				str.append("\t");
				str.append(result.getString(5));
				str.append("\t");
				str.append(result.getDouble(6));
				str.append("\t");
				str.append(result.getDouble(7));
				str.append("\t");
				str.append(result.getTimestamp(8));
				str.append("\n");
			}
			
			return str.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "N/A";
	}
	
	public String monthlyExpense() {
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM `inventory` WHERE (SELECT MONTH(RestockDate)) = (SELECT MONTH(CURDATE()))");
			ResultSet result = st.executeQuery();
			Double total = 0.0;
			StringBuilder str = new StringBuilder();
			str.append("Type");
			str.append("\t");
			str.append("Name");
			str.append("\t");
			str.append("Quantity");
			str.append("\t");
			str.append("UoM");
			str.append("\t");
			str.append("Price");
			str.append("\t");
			str.append("Inventory Value");
			str.append("\t");
			str.append("Restock Date");
			str.append("\n");
			while (result.next()) {
				str.append(result.getString(2));
				str.append("\t");
				str.append(result.getString(3));
				str.append("\t");
				str.append(result.getInt(4));
				str.append("\t");
				str.append(result.getString(5));
				str.append("\t");
				str.append(result.getDouble(6));
				str.append("\t");
				str.append(result.getDouble(7));
				str.append("\t");
				str.append(result.getTimestamp(8));
				str.append("\n");
				total += result.getDouble(7);
			}
			str.append("Total: " + total);
			
			
			return str.toString();
		} catch (Exception e) {
			System.out.println(e);
		}

		return "N/A";
	}
	
	public void conClose() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

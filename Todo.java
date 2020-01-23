package restaurantInventorySystem;

public @interface Todo {
/**
 * 1. Item/Ingredient management (add/update/delete): 
 * Measurement class?
 * 
 * 2. Inventory management (re-stock/usage recording)
 * restock quantity, unit conversion ( when restocking, items may have 
 * different UM so conversion may be needed
 * inventory quantity
 * 
 *Everytime user restocks, record items, dates, quantity, prices, UM 
 *Same for usage.
 *
 * 
 * 2.1 Database: store data into database
 * Inventory
 * Items
 * Usages
 * Purchases
 * 
 * 
 * 3. Reports: Expense and ingredient usage
 * Query from databases
 * 
 * 4. GUI: Simple GUI for UX.
 * Mediator for ui?
 * User can add, update, or delete items.
 * User can Restock.
 * User can record usage.
 * User can view records.
 * 
 */
}

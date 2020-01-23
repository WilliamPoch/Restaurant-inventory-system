
package restaurantInventorySystem.UI;

import javax.swing.*;
import restaurantInventorySystem.InventoryManager;



@SuppressWarnings("serial")
public class AddItem extends javax.swing.JFrame {
    
    private final InventoryManager inventory;


    public AddItem() {
        initComponents();
        this.inventory = new InventoryManager();
        this.inventory.setIngredient();
    }
    
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        itemPriceField = new javax.swing.JTextField();
        itemNameField = new javax.swing.JTextField();
        itemQuantityField = new javax.swing.JTextField();
        itemMeasurementField = new javax.swing.JTextField();
        ingredientButton = new javax.swing.JButton();
        consumableButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setText("Item Name");

        jLabel2.setText("Item Price");

        jLabel3.setText("Item Quantity");
        
        jLabel4.setText("Item unit of measurement");

        addButton.setBackground(new java.awt.Color(0, 255, 153));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        
        ingredientButton.setText("Add Ingredient");
        ingredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientActionPerformed(evt);
            }
        });
        
        consumableButton.setText("Add Consumable");
        consumableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consumableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ingredientButton)
                        .addComponent(consumableButton)
                        .addComponent(addButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        	.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    		.addComponent(itemMeasurementField)
                    		.addComponent(itemQuantityField)
                            .addComponent(itemPriceField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)                          
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(itemNameField)
                        .addGap(18, 18, 18)
                        .addComponent(itemPriceField)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(itemQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(itemMeasurementField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(ingredientButton)
                    .addComponent(consumableButton)
                    .addComponent(addButton))
                .addGap(34, 34, 34))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
        String name = itemNameField.getText();
        String price = itemPriceField.getText();
        String quantity = itemQuantityField.getText();
        String um = itemMeasurementField.getText();
        
        if(name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Item name cannot be empty");
            return;
        }
       
        if( Double.parseDouble(price) <= 0 || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price for the item");
            return;
        }
        
        if(quantity.isEmpty() || !quantity.chars().allMatch( Character::isDigit) 
                || Integer.parseInt(quantity) <=0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity for the item");
            return;
        }
        
        if(um.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a unit of measurement");
            return;
        }
        
       this.inventory.addItem(name, Integer.parseInt(quantity), um, Double.parseDouble(price));
       
        itemNameField.setText("");
        itemPriceField.setText("");
        itemQuantityField.setText("");
        itemMeasurementField.setText("");
        JOptionPane.showMessageDialog(this, "Item has been added");
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ItemManagement im = new ItemManagement();
        im.setVisible(true);
        this.setVisible(false);
    }
    
    private void ingredientActionPerformed(java.awt.event.ActionEvent evt) {
        this.inventory.setIngredient();
    }
    
    private void consumableActionPerformed(java.awt.event.ActionEvent evt) {
       this.inventory.setConsumable();
    }

    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JTextField itemPriceField;
    private javax.swing.JTextField itemQuantityField;
    private javax.swing.JTextField itemMeasurementField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private  javax.swing.JButton ingredientButton;
    private  javax.swing.JButton consumableButton;
 
}

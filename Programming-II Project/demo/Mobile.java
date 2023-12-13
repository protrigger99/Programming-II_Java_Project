package demo;

import ecomm.*;

public class Mobile extends DemoProduct {

    public Mobile(String Name, float Price, int Quantity) {
        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
        // generating uniqueID
        ProductID = Integer.toBinaryString(counter);
        counter++;
    }

    // getters
    public String getName() {
        return this.Name;
    }

    public String getProductID() {
        return this.ProductID;
    }

    public float getPrice() {
        return this.Price;
    }

    public int getQuantity() {
        return this.Quantity;
    }

    public Globals.Category getCategory() {
        return Globals.Category.Mobile;
    }

    // reducing quantity
    public void sellQuantity(int quantity) {
        this.Quantity -= quantity;
    }

    private String Name, ProductID;
    private float Price;
    private int Quantity;
    private static int counter = 32;
}
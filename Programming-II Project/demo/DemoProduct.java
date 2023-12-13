package demo;

import ecomm.*;

//child class of Product made to give selling functionality
public abstract class DemoProduct extends Product {
    public abstract Globals.Category getCategory();

    public abstract String getName();

    public abstract String getProductID();

    public abstract float getPrice();

    public abstract int getQuantity();

    // reducing quantity
    public abstract void sellQuantity(int quantity);
}
package demo;

import java.util.ArrayList;
import ecomm.*;

public class SellerG extends Seller {
    public SellerG(String id) {
        super(id);
        this.products = new ArrayList<DemoProduct>();
        // hardcoded products
        Book b1 = new Book("RD_Sharma", 10.6f, 20);
        Book b2 = new Book("Feynman_Lectures", 15, 20);
        Book b3 = new Book("Classmate", 20, 20);
        Mobile m1 = new Mobile("OnePlus", 5, 10);
        Mobile m2 = new Mobile("Samsung", 10.7f, 10);
        products.add(b1);
        products.add(b2);
        products.add(b3);
        products.add(m1);
        products.add(m2);
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        ArrayList<Product> thisList = new ArrayList<Product>();
        // making list of products of given category
        for (DemoProduct prd : this.products) {
            if (prd.getCategory() == whichOne)
                thisList.add(prd);
        }
        return thisList;
    }

    public boolean buyProduct(String productID, int quantity) {
        // checking if product with given ID and sufficient quantity is present
        for (DemoProduct prd : this.products) {
            if (prd.getProductID().equals(productID) && prd.getQuantity() >= quantity) {
                // reduce quantity
                prd.sellQuantity(quantity);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> getProducts() {
        // upcasting DemoProduct list to Product list
        ArrayList returnList = new ArrayList<Product>();
        for (DemoProduct prd : this.products)
            returnList.add(prd);
        return returnList;
    }

    public void addPlatform(Platform thePlatform) {
        // adding platform
        if (thePlatform.addSeller(this))
            this.thisPlatform = thePlatform;
    }

    private ArrayList<DemoProduct> products;
    private Platform thisPlatform;
}

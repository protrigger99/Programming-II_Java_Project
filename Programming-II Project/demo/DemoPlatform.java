package demo;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class DemoPlatform extends Platform {

	public DemoPlatform() {
		this.sellers = new ArrayList<Seller>();
	}

	// adding seller if not present already
	@Override
	public boolean addSeller(Seller aSeller) {
		for (Seller slr : this.sellers) {
			if (slr == aSeller)
				return false;
		}
		this.sellers.add(aSeller);
		return true;
	}

	@Override
	public void processRequests() {

		ArrayList<String> outputs = new ArrayList<String>();

		// try catch block to scan from PortalToPlatform
		try {
			File toPf = new File(Globals.toPlatform);
			Scanner fread = new Scanner(toPf);
			while (fread.hasNextLine()) {
				String por, rID, cmd, proID;
				int numI;

				// splitting the line
				String string = fread.nextLine();
				String[] inputLine = string.split(" ");

				// portal, request ID and command
				por = inputLine[0];
				rID = inputLine[1];
				cmd = inputLine[2];

				// Start
				if (cmd.equals("Start")) {
					String str = por + " " + rID + " ";
					Globals thisG = new Globals();
					// making list of all categories
					for (Globals.Category categ : Globals.Category.values())
						str += thisG.getCategoryName(categ) + " ";
					outputs.add(str);
				}

				// List
				else if (cmd.equals("List")) {
					// Category to be listed
					String catstr = inputLine[3];
					Globals.Category prdCat = Globals.Category.Book;
					if (catstr.equals("Mobile"))
						prdCat = Globals.Category.Mobile;
					else if (catstr.equals("Book"))
						prdCat = Globals.Category.Book;

					// making list of given category and output
					ArrayList<Product> prdList = new ArrayList<Product>();
					for (Seller slr : this.sellers)
						prdList.addAll(slr.findProducts(prdCat));
					for (Product prd : prdList) {
						String str = por + " " + rID + " ";
						str += prd.getName() + " " + prd.getProductID() + " " + prd.getPrice() + " "
								+ prd.getQuantity();
						outputs.add(str);
					}
				}

				// Buy
				else if (cmd.equals("Buy")) {
					boolean succ = false;

					// Product ID and quantity to be bought
					proID = inputLine[3];
					numI = Integer.parseInt(inputLine[4]);

					// checking among all sellers for given ID and quantity
					for (Seller slr : this.sellers) {
						if (slr.buyProduct(proID, numI)) {
							succ = true;
							break;
						}
					}

					// formatted output
					String str = por + " " + rID;
					if (succ)
						str += " Success";
					else
						str += " Failure";
					outputs.add(str);
				}
			}
			fread.close();
		} catch (FileNotFoundException exc) {
			exc.printStackTrace();
		}

		// erasing PortalToPlatform, else same output will be repeated on next Check
		try {
			FileWriter fwrite2 = new FileWriter(Globals.toPlatform);
			fwrite2.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		// try catch block to write to PlatformToPortal
		try {
			FileWriter fwrite = new FileWriter(Globals.fromPlatform, true);
			for (String line : outputs)
				fwrite.write(line + " \n");
			fwrite.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	private ArrayList<Seller> sellers;
}

import demo.*;
import ecomm.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PlatformMain {

	public static void main(String[] args) {

		Platform pf = new DemoPlatform();

		// adding sellers
		Seller s1 = new SellerG("Seller1");
		s1.addPlatform(pf);
		pf.addSeller(s1);

		Seller s2 = new SellerP("Seller2");
		s2.addPlatform(pf);
		pf.addSeller(s2);

		Seller s3 = new SellerR("Seller3");
		s3.addPlatform(pf);
		pf.addSeller(s3);

		// erasing files to remove any leftover lines from before
		try {
			FileWriter fwrite = new FileWriter(Globals.toPlatform);
			fwrite.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		try {
			FileWriter fwrite = new FileWriter(Globals.fromPlatform);
			fwrite.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		while (true) {
			String str;
			str = sc.next();
			if (str.equals("Check"))
				pf.processRequests();
			if (str.equals("End")) // not used
				break;
		}
		sc.close();
	}
}

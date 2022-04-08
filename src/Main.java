import java.util.*;

public class Main {

	Scanner sc = new Scanner(System.in);
	Vector<Parts> parts = new Vector<Parts>();
	Vector<Customer> customers = new Vector<Customer>();
	Vector<TransactionHistory> transHistory = new Vector<TransactionHistory>();
	Vector<Air> airs = new Vector<Air>();
	Vector<Sea> seas = new Vector<Sea>();
	Vector<Land> lands = new Vector<Land>();
	Vector<Shipping> ships = new Vector<Shipping>();
	//method untuk validasi input yang dimasukkan harus integer (string akan error)
	public void clearScreen()
	{
		for (int i=0; i<30; i++) System.out.println("");
	}
	public int validateIntInput(int input, int type)
	{
		int value = 1;
		while(value == 1)
		{
			//variabel type digunakan untuk mengetahui pertanyaan (sebelum input) apa yang harus di print
			if (type == 1) System.out.print("Choose Menu: ");
			else if (type == 2) System.out.print("Input part's price to buy [at least 100 or more]: ");
			else if (type == 3) System.out.print("Input part's quantity to buy [at least 1 or more]: ");
			else if (type == 4) System.out.print("Quantity to sell: ");
			else if (type == 5) System.out.print(">> ");
			try {
				input = sc.nextInt();
				if (input == (int) input) 
				{
					value = 0;
					break;
				}
			}
			catch (Exception e) {
				System.out.println("Your input must be integer!");
				value = 1;
			}
			finally {
				sc.nextLine();
			}
		}
		return input;	
	}
	public void addTransHistory(String TransType, int qty, String partID, String custID, String supplierName, int totalPrice, String paymentMethod, String shipID)
	{
		String id = "TR" + String.format("%03d", transHistory.size()+1);
		TransType.toUpperCase();
		transHistory.add(new TransactionHistory(id, TransType, qty, partID, custID, supplierName, totalPrice, paymentMethod, shipID));
	}
	public void printHeader(String headerMenu)
	{
		if (headerMenu.length() > 0)
		{
			String header = " => " + headerMenu;
			System.out.println("Group 22 Sparepart" + header);
		}
		else System.out.println("Group 22 Sparepart");
		System.out.println("=========================");
	}
	public void printMenu()
	{
		clearScreen();
		printHeader("");
		System.out.println("1. Buy Parts");
		System.out.println("2. Sell Parts");
		System.out.println("3. View Member");
		System.out.println("4. View Available Parts");
		System.out.println("5. View Transaction History");
		System.out.println("6. View Shipping List");
		System.out.println("7. Exit");
	}
	public void viewTransactionHistory() 
	{
        printHeader("View Transaction History");
        if(transHistory.isEmpty()) {
            System.out.println("No Data Transaction ");
            System.out.println("Press Enter To Continue...");
        
        }else {
            System.out.println("============================================================================================================================================");
            System.out.printf("| %-15s | %-10s | %-15s | %-15s | %-20s | %-15s | %-15s | %-10s |\n","Transaction ID","Type", "Quantity","Customer ID","Part ID","Supplier Name","Payment Method","Price");
            System.out.println("============================================================================================================================================");
            
            for(TransactionHistory th : transHistory) {
                if(th.getTransType().equalsIgnoreCase("Buy")) {
                    System.out.printf("| %-15s | %-10s | %-15s | %-15s | %-20s | %-15s | %-15s | %-10s |\n",th.getTransID(),th.getTransType(),th.getTransQty(),th.getCustID(),th.getPartID(),th.getSupplierName(),th.getPaymentMethod(),th.getTransPrice());                    
                }else if(th.getTransType().equalsIgnoreCase("Sell")) {
                    System.out.printf("| %-15s | %-10s | %-15s | %-15s | %-20s | %-15s | %-15s | %-10s |\n",th.getTransID(),th.getTransType(),th.getTransQty(),th.getCustID(),th.getPartID()," ",th.getPaymentMethod(),th.getTransPrice());                    
                }
            }        
            System.out.println("============================================================================================================================================");
            System.out.println("Press Enter To Continue...");
        }
        
        sc.nextLine();
    }
	public void viewAvailableParts()
	{
		printHeader("View Available Parts");
		if (parts.size() > 0) 
		{
			for (int i=0; i<parts.size(); i++)
			{
				System.out.printf("%d.\n", i+1);
				System.out.println("PartID: " + parts.get(i).getPartID());
				System.out.println("Part Name: " + parts.get(i).getPartName());
				System.out.println("Part Stock: " + parts.get(i).getPartQty());
				System.out.println("Part Price (each): Rp " + parts.get(i).getPartPrice());
			}
			System.out.println("===================================================");
			System.out.println("Press Enter to Continue...");
		}
		else System.out.print("There is no part in database"); 
		sc.nextLine();
	}
	public String AddMember() {
		String custName = null;
		String custStatus = null;
		String custID = null;
		double diskon= 1;
		do {
			System.out.print("Input Customer Name: ");
			custName = sc.nextLine();
		} while (custName.length()<5);
		
		do {
			System.out.print("Input Customer Status [ premium | gold ]: ");
			custStatus = sc.nextLine();
		} while (!custStatus.equals("premium") && !custStatus.equals("gold"));
		
		 
		
		custID = "CU" + String.format("%03d", (int) Math.floor(Math.random()*1000));
		System.out.print("Member successfully added with ID:"+custID+"!");
		sc.nextLine();
		
		customers.add(new Customer(custID, custName, custStatus, diskon));
		return custID;
	}
	
	public boolean CheckSearchID(String search) {
			
			if(search.length()!=5)
			{
				return false;
			}
			
			if(search.charAt(0)!='C')
			{
				return false;
			}
			if(search.charAt(1)!= 'U')
			{
				return false;
			}
			
			for (int i = 2; i < 5; i++) {
				char temp = search.charAt(i);
				if(Character.isDigit(temp)==false)
				{
					return false;
				}
			}
			
			return true;
		}
	
	public void DeleteMember(Vector <Customer> customers) 
	{
		String deleteID = null;
		System.out.print("Input ID you want to delete: ");
		deleteID = sc.nextLine();
		
		if(CheckSearchID(deleteID)==false)
		{
			System.out.println("Invalid");
			System.out.print("Press Enter to continue...");
			sc.nextLine();
			return;
		}
		
		for (int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getCustID().equals(deleteID))
			{
				customers.remove(i);
				System.out.print("Success remove Member");
				sc.nextLine();
				return;
			}
		}
		
		System.out.print("Press Enter to Continue...");
		sc.nextLine();
		return;
	}
	
	public void UpdateMember(Vector<Customer> customers) {
		String updateID = null;
		String custName = null;
		String custStatus = null;
		
		System.out.print("Input ID you want to update: ");
		updateID = sc.nextLine();
		
		if(CheckSearchID(updateID)==false)
		{
			System.out.println("Invalid");
			System.out.print("Press Enter to continue...");
			sc.nextLine();
			return;
		}
		
		Customer tempCust = null;
		
		for (Customer customer : customers) {
			if(customer.getCustID().equals(updateID))
			{
				tempCust = customer;
				break;
			}
		}
		
		if(tempCust == null)
		{
			System.out.println("Not Found!");
			System.out.print("Press Enter to continue...");
			sc.nextLine();
			return;
		}
		
		do {
			System.out.print("Input New Name: ");
			try {
				custName = sc.nextLine();
			} catch (Exception e) {
				sc.nextLine();
			}
		} while (custName.length()<5);
		
		do {
			System.out.print("Input New Status [ premium | gold ]: ");
			try {
				custStatus = sc.nextLine();
			} catch (Exception e) {
				sc.nextLine();
			}
		} while (!custStatus.equals("premium") && !custStatus.equals("gold"));
		
		tempCust.setCustName(custName);
		tempCust.setCustStatus(custStatus);
		System.out.printf("Successfully updated with ID %s!\n", tempCust.getCustID());
		System.out.print("Press Enter to continue...");
		sc.nextLine();
	}
	
	public void MemberMenu() {
	    System.out.println("1. Add Member");
	    System.out.println("2. Update Member");
	    System.out.println("3. Remove Member");
	    System.out.println("4. Return to Main Menu");
	}
	
	public void ViewMember() 
	{
		int opt = 0;
		//
		while(true)
		{
			clearScreen();
			printHeader("View Member");
			if(customers.isEmpty())
			{
				System.out.println("No Member Found!");
			}
			else
			{
				System.out.println("=======================================================");
				System.out.printf("| %-10s | %-20s | %-10s |\n", "CustomerID", "Customer Name", "Customer Status");
				System.out.println("=======================================================");
				for (Customer customer : customers) {
					System.out.printf("| %-10s | %-20s | %-15s |\n", customer.getCustID(), customer.getCustName(), customer.getCustStatus());
				}
				System.out.println("=======================================================");
			}
			//
			MemberMenu();
			opt = validateIntInput(opt, 5);
			
			if(opt==1)
			{
				AddMember();
			}
			else if(opt==2)
			{
				UpdateMember(customers);
			}
			else if(opt == 3)
			{
				DeleteMember(customers);
			}
			else if(opt == 4)
			{
				return;
			}
		}
	}
	
	public void exitMenu()
	{
		System.out.println("====================================================================================================================================");
        System.out.println("#################    ######      ######           ###########           #########       ######    ######    ######      ######      ");
        System.out.println("#################    ######      ######          ###### ######          ###### ###      ######    ######   ######    ############   ");
        System.out.println("      ######         ######      ######         ######   ######         ######  ###     ######    ######  ######    ######          ");
        System.out.println("      ######         ##################        ######     ######        ######   ###    ######    #############      ############   ");
        System.out.println("      ######         ##################       ###################       ######    ###   ######    ############          ############");
        System.out.println("      ######         ######      ######      #####################      ######     ###  ######    ######  ######             ###### ");
        System.out.println("      ######         ######      ######     ######           ######     ######      ### ######    ######   ######     ############  ");
        System.out.println("      ######         ######      ######    ######             ######    ######       #########    ######    ######       ######     ");
        System.out.println("====================================================================================================================================");
	}
	public void buyParts()
	{
		String partID = "PT", name, supplier, paymentMethod;
		int price = 0, qty = 0;
		printHeader("Buy Parts");
		//validate part ID
		while(true)
		{
			partID += String.format("%03d", (int) Math.floor(Math.random()*1000));
			int valid = 1;
			if (parts.size() > 0)
			{
				for (int i=0; i<parts.size(); i++)	
				{
					if(parts.get(i).getPartID().equals(partID))
					{
						valid = 0;
						break;
					}
				}
				if (valid == 1) break;
			}
			else break;
		}
		//validate part name
		while(true)
		{
			System.out.print("Input Part Name [not less than 2 characters]: ");
			name = sc.nextLine();
			if (name.length() >= 2) break;
		}
		//validate part price
		while(true)
		{
			price = validateIntInput(price, 2);
			if (price >= 100) 
			{
				break;
			}
		}
		//validate part qty
		while(true)
		{
			qty = validateIntInput(qty, 3);
			if (qty > 0) break;
		}
		//validate supplier
		while(true)
		{
			System.out.print("Input supplier's name [must be more than 2 characters]: ");
			supplier = sc.nextLine();
			if (supplier.length() > 2) break;
		}
		while(true)
		{
			System.out.print("Choose Your Payment Method [M-Banking | Cash | Credit Card]: ");
			paymentMethod = sc.nextLine();
			if (paymentMethod.equals("M-Banking") || paymentMethod.equals("Cash") || paymentMethod.equals("Credit Card")) break;
		}
		int totalPrice = qty*price; 
		System.out.print("Successfully purchased " + qty + " " + name + " from " + supplier + " with total cost: Rp " + totalPrice + "!"); sc.nextLine();
		price = (int) (price + (price*0.8));
		parts.add(new Parts(partID, name, price, qty, supplier));
		addTransHistory("BUY", qty, partID, "", supplier, totalPrice, paymentMethod, "");
	}
	
	
	public void sellParts()
	{
		String id, custID = null, shipID = "SH" + String.format("%03d", (int) Math.floor(Math.random()*1000)), memberSelect, paymentMethod, shipping, shipperName;
		int qty = 0, totalPrice = 0, index = 0;
		clearScreen();
		printHeader("Sell Parts");
		viewAvailableParts();
		
		if (parts.size() > 0)
		{
			//validate inputed id is valid
			while(true)
			{
				System.out.print("Input PartID to Sell: ");
				id = sc.nextLine();
				int valid=0;
				for(int i=0 ;i<parts.size(); i++)
				{
					if (id.compareTo(parts.get(i).getPartID()) == 0)
					{
						index = i;
						valid = 1;
						break;
					}
				}
				if (valid == 1) break;
			}
			//validate qty nya ada dan qty tidak lebih dari jumlah stock
			while(true)
			{
				qty = validateIntInput(qty, 4);
				//langsung kurangin stock 
				if (qty > 0 && qty <= parts.get(index).getPartQty()) 
				{
					parts.get(index).setPartQty(parts.get(index).getPartQty()-qty);
					break;
				}
			}
			//melakukan perhitungan total transaksi
			totalPrice = qty*parts.get(index).getPartPrice();
			//mengecek member status dan mengaplikasikan diskon sesuai member yang berlaku
			while(true)
			{
				System.out.print("Do you have any membership? [Yes or No]: ");
				memberSelect = sc.nextLine();
				if (memberSelect.equalsIgnoreCase("Yes"))
				{
					
					do {
						System.out.print("Please input your Customer ID [CUXXX]: ");
						custID = sc.nextLine();
					} while (!CheckSearchID(custID));
					
					Customer tempCust = null;
					
					for (Customer customer : customers) {
						if(customer.getCustID().equals(custID))
						{
							tempCust = customer;
							break;
						}
					}
					
					if(tempCust == null)
					{
						System.out.println("Not Found!");
						System.out.print("Press Enter to add new member...");
						sc.nextLine();
						custID = AddMember();
					}
					
					
					for (int i=0; i<customers.size(); i++)
					{
						if (custID.compareTo(customers.get(i).getCustID()) == 0)
						{
							if (customers.get(i).getCustStatus().equalsIgnoreCase("Premium"))
							{
								premiumCust premium = new premiumCust();
								totalPrice = (int) (totalPrice - (totalPrice*premium.diskon()));
								System.out.println("Yay, here is your 10% discount for your premium membership");
							}
							else if (customers.get(i).getCustStatus().equalsIgnoreCase("Gold"))
							{
								goldCust gold = new goldCust();
								totalPrice = (int) (totalPrice - (totalPrice*gold.diskon()));
								System.out.println("Yay, here is your 5% discount for your gold membership");
							}
						}
					}
					break;
				}
				else if (memberSelect.equalsIgnoreCase("No")) 
						{
							custID = "CU"+String.format("%03d", (int) Math.floor(Math.random()*1000));
							break;
						}
				else System.out.println("Choose Yes or No only!");
			}
			//validate payment method
			while(true)
			{
				System.out.print("Choose Your Payment Method [M-Banking | Cash | Credit Card]: ");
				paymentMethod = sc.nextLine();
				if (paymentMethod.equals("M-Banking") || paymentMethod.equals("Cash") || paymentMethod.equals("Credit Card"))
				{
					break;
				}
			}
			//validate shipping
			while(true)
			{
				System.out.print("Choose Your Shipping Method [ Air | Land | Sea ]: ");
				shipping = sc.nextLine();
				if (shipping.equals("Air") || shipping.equals("Land") || shipping.equals("Sea"))
				{
					while(true)
					{
						System.out.print("Input Your Shipper's Name [cannot be empty]: ");
						shipperName = sc.nextLine();
						if (shipperName.length() > 0) break;
					}
					if (shipping.equals("Air"))
					{
						airs.add(new Air(shipID, shipperName, shipping));
						totalPrice += airs.get(airs.size()-1).getShippingCost();
						System.out.println("Added Air Shipping Cost to Total Price!");
					}
					else if (shipping.equals("Land"))
					{
						lands.add(new Land(shipID, shipperName, shipping));
						totalPrice += lands.get(lands.size()-1).getShippingCost();
						System.out.println("Added Land Shipping Cost to Total Price!");
					}
					else if (shipping.equals("Sea"))
					{
						seas.add(new Sea(shipID, shipperName, shipping));
						totalPrice += seas.get(seas.size()-1).getShippingCost();
						System.out.println("Added Sea Shipping Cost to Total Price!");
					}
					break;
				}
			}
			if (parts.get(index).getPartQty() <= 0) parts.remove(index);
			//tambahkan ke history transaksi
			addTransHistory("SELL", qty, id, custID, "", totalPrice, paymentMethod, shipID);
			System.out.println("Total Price for your transaction: " + totalPrice);
			System.out.print("Your transaction has been done successfully!"); sc.nextLine();
			
		}
	}
	
	
	public void viewShippingList() {
        printHeader("Shipping List");
        if(lands.isEmpty() && seas.isEmpty() && airs.isEmpty()) {
            System.out.println("No Shipping Data");
            System.out.println("================");
            System.out.println("Press Enter To Continue...");
        }else {
        	
            System.out.println("============================================================================");
            System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s |\n","Ship ID","Shipping Name", "Shipping Method","Cost", "Cust ID");
            System.out.println("============================================================================");
            
            for(Land l : lands) {
            	TransactionHistory tempTH = null;
            	if(tempTH == null)
            	{
            		for (TransactionHistory TH : transHistory) {
            			if(TH.getShipID().equals(l.getShipID()))
            			{
            				tempTH = TH;
            				break;
            			}
            		}
            	}
                System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s |\n",l.getShipID(),l.getShippingName(),l.getShippingMethodName(),l.getShippingCost(), tempTH.getCustID());
            }
            
            for(Sea s : seas) {
            	TransactionHistory tempTH = null;
            	if(tempTH == null)
            	{
            		for (TransactionHistory TH : transHistory) {
            			if(TH.getShipID().equals(s.getShipID()))
            			{
            				tempTH = TH;
            				break;
            			}
            		}
            	}
            	System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s |\n",s.getShipID(),s.getShippingName(),s.getShippingMethodName(),s.getShippingCost(), tempTH.getCustID());
            }
            for(Air a : airs) {
            	TransactionHistory tempTH = null;
            	if(tempTH == null)
            	{
            		for (TransactionHistory TH : transHistory) {
            			if(TH.getShipID().equals(a.getShipID()))
            			{
            				tempTH = TH;
            				break;
            			}
            		}
            	}
            	System.out.printf("| %-10s | %-15s | %-15s | %-10s | %-10s |\n",a.getShipID(),a.getShippingName(),a.getShippingMethodName(),a.getShippingCost(), tempTH.getCustID());
            }
            System.out.println("============================================================================");
            System.out.println("Press Enter To Continue...");
        }
        sc.nextLine();
    }
	
	public Main() {
		// TODO Auto-generated constructor stub
		int choice = 0;
		while (true)
		{
			printMenu();
			choice = validateIntInput(choice, 1);
			if (choice == 1) buyParts();
			else if (choice == 2) sellParts();
			else if (choice == 3) ViewMember();
			else if (choice == 4) viewAvailableParts();
			else if (choice == 5) viewTransactionHistory();
			else if (choice == 6) viewShippingList();
			else if (choice == 7) 
			{
				exitMenu();
				break;
			}
			else System.out.println("Wrong input range!");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();

	}

}

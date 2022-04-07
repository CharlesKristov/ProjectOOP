import java.util.*;

import javax.swing.text.View;

public class Main {

	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
    Vector<Parts> parts = new Vector<Parts>();
    Vector<Customer> customers = new Vector<Customer>();
    Vector<TransactionHistory> transHistory = new Vector<TransactionHistory>();
    Vector<Air> airs = new Vector<Air>();
    Vector<Sea> seas = new Vector<Sea>();
    Vector<Land> lands = new Vector<Land>();
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
			else if (type == 4) System.out.print("Quantity to sell: ");
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
	
	public void addTransHistory(String TransType, int qty, String partID, String custID, String supplierName, int totalPrice, String paymentMethod)
    {
        String id = "TR" + String.format("%03d", transHistory.size()+1);
        TransType.toUpperCase();
        transHistory.add(new TransactionHistory(id, TransType, qty, partID, custID, supplierName, totalPrice, paymentMethod));
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
        }
        else System.out.print("There is no part in database"); 
        sc.nextLine();
    }
	
	public void sellParts()
	{
		String id, custID = null, shipID = "SH" + String.format("%03d", transHistory.size()+1), memberSelect, paymentMethod, shipping, shipperName;
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
					parts.get(index).setPartQty(qty-parts.get(index).getPartQty());
					break;
				}
			}
			//melakukan perhitungan total transaksi
			totalPrice = qty*parts.get(index).getPartPrice();
			//mengecek member status dan mengaplikasikan diskon sesuai member yang berlaku
			while(true)
			{
				System.out.println("Do you have any membership? [Yes or No]");
				memberSelect = sc.nextLine();
				if (memberSelect.equalsIgnoreCase("Yes"))
				{
					
					System.out.print("Please input your member ID: ");
					custID = sc.nextLine();
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
				else if (memberSelect.equalsIgnoreCase("No")) break;
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
						totalPrice += airs.get(index).getShippingCost();
						airs.add(new Air(shipID, shipping, shipperName, airs.get(index).getShippingCost()));
						System.out.println("Added Air Shipping Cost to Total Price!");
					}
					else if (shipping.equals("Land"))
					{
						totalPrice += airs.get(index).getShippingCost();
						lands.add(new Land(shipID, shipping, shipperName, airs.get(index).getShippingCost()));
						System.out.println("Added Land Shipping Cost to Total Price!");
					}
					else if (shipping.equals("Sea"))
					{
						totalPrice += seas.get(index).getShippingCost();
						seas.add(new Sea(shipID, shipping, shipperName, airs.get(index).getShippingCost()));
						System.out.println("Added Sea Shipping Cost to Total Price!");
					}
					break;
				}
			}
			if (parts.get(index).getPartQty() <= 0) parts.remove(index);
			//tambahkan ke history transaksi
			addTransHistory("SELL", qty, id, custID, "", totalPrice, paymentMethod);
			System.out.println("Total Price for your transaction: " + totalPrice);
			System.out.print("Your transaction has been done successfully!"); sc.nextLine();
			
		}
	}
	
	public void AddMember() {
		String custName = null;
		String custStatus = null;
		String custID = null;
		double diskon= 1;
		do {
			System.out.println("Input Customer Name: ");
			custName = sc.nextLine();
		} while (custName.length()<5);
		
		do {
			System.out.println("Input Customer Status [none|premium|gold]: ");
			custStatus = sc.nextLine();
		} while (!custStatus.equals("none") && !custStatus.equals("premium") && !custStatus.equals("gold"));
		
		 
		
		custID = "CU" + String.format("%03d", rand.nextInt(1000));
		System.out.println("Member successfully added!");
		
		sc.nextLine();
		
		customers.add(new Customer(custID, custName, custStatus, diskon));
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
	
	public void DeleteMember(Vector <Customer> customers) {
		String deleteID = null;
		System.out.println("Input ID you want to delete: ");
		deleteID = sc.nextLine();
		
		if(CheckSearchID(deleteID)==false)
		{
			System.out.println("Invalid");
			System.out.println("Press Enter to continue...");
			sc.nextLine();
			return;
		}
		
		for (int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getCustID().equals(deleteID))
			{
				customers.remove(i);
				System.out.println("Success remove Member");
				sc.nextLine();
				return;
			}
		}
		
		System.out.println("Press Enter to Continue...");
		sc.nextLine();
		return;
	}
	
	public void UpdateMember(Vector<Customer> customers) {
		String updateID = null;
		String custName = null;
		String custStatus = null;
		
		System.out.println("Input ID you want to update: ");
		updateID = sc.nextLine();
		
		if(CheckSearchID(updateID)==false)
		{
			System.out.println("Invalid");
			System.out.println("Press Enter to continue...");
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
			System.out.println("Press Enter to continue...");
			sc.nextLine();
			return;
		}
		
		do {
			System.out.println("Input New Name: ");
			System.out.print(">>");
			try {
				custName = sc.nextLine();
			} catch (Exception e) {
				sc.nextLine();
			}
		} while (custName.length()<5);
		
		do {
			System.out.println("Input New Status [none|premium|gold]: ");
			System.out.print(">>");
			try {
				custStatus = sc.nextLine();
			} catch (Exception e) {
				sc.nextLine();
			}
		} while (!custStatus.equals("none") && !custStatus.equals("premium") && !custStatus.equals("gold"));
		
		tempCust.setCustName(custName);
		tempCust.setCustStatus(custStatus);
		System.out.printf("Successfully updated with ID %s!\n", tempCust.getCustID());
		System.out.println("Press Enter to continue...");
		sc.nextLine();
	}
	
	public void MemberMenu() {
	    System.out.println("1. Add Member");
	    System.out.println("2. Update Member");
	    System.out.println("3. Remove Member");
	    System.out.println("4. Return to Main Menu");
	}
	
	public void ViewMember() {
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
		
		
		int opt=-1;
		do {
			MemberMenu();
			try {
				opt = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				// TODO: handle exception
				sc.nextLine();
			}
			
			if(opt==1)
			{
				AddMember();
				ViewMember();
			}
			else if(opt==2)
			{
				UpdateMember(customers);
				ViewMember();
			}
			else if(opt == 3)
			{
				DeleteMember(customers);
				ViewMember();
			}
			else if(opt == 4)
			{
				return;
			}
			
		} while (opt != 4);
		return;
	}
	
	public void viewTransactionHistory() {
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
	
	
	
	public Main() {
		// TODO Auto-generated constructor stub
		int choice = 0;
		while (true)
		{
			printMenu();
			choice = validateIntInput(choice, 1);
			if (choice == 1)
			{
				
			}
			else if (choice == 2)
			{
				sellParts();
			}
			else if (choice == 3)
			{
				ViewMember();
			}
			else if (choice == 4) 
			{
				viewAvailableParts();
			}
			else if (choice == 5)
			{
				
			}
			else if (choice == 6)
			{
				
			}
			else if (choice == 7)
			{
				break;
			}
			else 
			{
				System.out.println("Wrong input range!");
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}

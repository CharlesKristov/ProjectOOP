import java.util.*;

public class Main {

	Scanner sc = new Scanner(System.in);
	Vector<Parts> parts = new Vector<Parts>();
	Vector<Customer> customers = new Vector<Customer>();
	Vector<TransactionHistory> transHistory = new Vector<TransactionHistory>();
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
		String id = "TR";
		TransType.toUpperCase();
		if (transHistory.size() > 0 && transHistory.size() < 10) id += "00" + transHistory.size();
		else if (transHistory.size() > 9 && transHistory.size() < 100) id += "0" + transHistory.size();
		else if (transHistory.size() > 99 && transHistory.size() < 1000)  id += transHistory.size();
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
		System.out.println("6. View Shipping Method");
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
		String id, custID = null, memberSelect, paymentMethod;
		int qty = 0, totalPrice = 0, index = 0;
		clearScreen();
		printHeader("Sell Parts");
		viewAvailableParts();
		//validate inputed id is valid
		while(true)
		{
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
			if (paymentMethod.equalsIgnoreCase("M-Banking") || paymentMethod.equalsIgnoreCase("Cash") || paymentMethod.equalsIgnoreCase("Credit Card"))
			{
				break;
			}
		}
		if (parts.get(index).getPartQty() <= 0) parts.remove(index);
		//tambahkan ke history transaksi
		addTransHistory("SELL", qty, id, custID, "", totalPrice, paymentMethod);
		System.out.println("Total Price for your transaction: " + totalPrice);
		System.out.print("Your transaction has been done successfully!"); sc.nextLine();
		
		
	}
	
	public Main() {
		// TODO Auto-generated constructor stub
		int choice = 0;
		while (true)
		{
			printMenu();
			choice = validateIntInput(choice, 1);
			if (choice == 1)
			else if (choice == 2) sellParts();
			else if (choice == 3)
			else if (choice == 4) viewAvailableParts();
			else if (choice == 5)
			else if (choice == 6)
			else if (choice == 7) break;
			else System.out.println("Wrong input range!");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

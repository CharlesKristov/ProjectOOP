import java.util.*;

public class Main {

	Scanner sc = new Scanner(System.in);
	//method untuk validasi input yang dimasukkan harus integer (string akan error) 
	public int validateIntInput(int input, int type)
	{
		int value = 1;
		while(value == 1)
		{
			//variabel type digunakan untuk mengetahui pertanyaan (sebelum input) apa yang harus di print
			if (type == 1) System.out.print("Choose Menu: ");
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
	public void printHeader()
	{
		System.out.print("Group 22 Sparepart");
		System.out.println("=========================");
	}
	public void printMenu()
	{
		printHeader();
		System.out.println("1. Buy Parts");
		System.out.println("2. Sell Parts");
		System.out.println("3. View Member");
		System.out.println("4. View Available Parts");
		System.out.println("5. View Transaction History");
		System.out.println("6. View Shipping Method");
		System.out.println("7. Exit");
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
				
			}
			else if (choice == 3)
			{
				
			}
			else if (choice == 4)
			{
				
			}
			else if (choice == 5)
			{
				
			}
			else if (choice == 6)
			{
				
			}
			else if (choice == 7) break;
			else System.out.println("Wrong input range!");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

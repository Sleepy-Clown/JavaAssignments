import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		BankAccounts bank = new BankAccounts("Generic Co. Banking");
		
		System.out.println("Welcome to the "+bank.getBankName()+" Account Terminal\n");
		System.out.println("Enter an option number: ");
		System.out.println("1. Load Account Data\r\n" + "-1. Exit Program");
		
		int option = input.nextInt();
		
		if(option == 1) {
			Menu.enter();
			Menu.menu();
		}
		
		else if(option == -1) {
			System.out.println("Closing Terminal..."); input.close();
		}
		else {
			do {
				System.out.println("Enter an option number: ");
				System.out.println("1. Load account data\r\n" + "-1. Exit Program");
				option = input.nextInt();
			}while(option != 1 && option != -1);
		}
	
	}
}
	
class Menu {

	static Scanner input = new Scanner(System.in);
	static BankAccounts bank = new BankAccounts("Generic Co. Banking");
	
	public Menu() {}
	
	public static void menu() throws IOException {
		System.out.println("\nEnter an option number: ");
		System.out.println("1. Add new accounts\r\n"
						+ "2. Read accounts data\r\n"
						+ "3. Retrieve specific account data based on account number\r\n"
						+ "4. Create reversed account data file\r\n"
						+ "-1. Exit Program");
			
		int option = input.nextInt();
			
		switch(option % 4) {
			case -1: System.out.println("Closing Terminal..."); input.close(); System.exit(1); break;
			case 0: option4(); break;
			case 1: option1(); break;
			case 2: option2(); break;
			case 3: option3();
		}
	}
		
	public static void enter() throws IOException { //Load account data
		bank.addAccounts();
	}//Close enter
		
	public static void option1() throws IOException { //Add new account
		System.out.println("\nIs this account a Checking(C) or Savings(S) account? (Enter C or S):");
		String cos = input.next(); //cos stands for checking or savings, and is only used for account type
		System.out.println("\nEnter the Account Name (First_Last):");
		String name = input.next();
		System.out.println("\nEnter the Account Number (5-digit number):");
		int num = input.nextInt();
		System.out.println("\nEnter the Account Balance:");
		double bal = input.nextDouble();
		
		boolean Check = (cos.equalsIgnoreCase("C"));
		boolean Save = (cos.equalsIgnoreCase("S"));
		
		if(Check) {
			String accType = "checking";
			System.out.println("\nEnter the Account Check Limit:");
			int cl = input.nextInt();
			
			bank.addNewCheckingAccount(name, num, bal, accType,cl);
			bank.addNewAccount(name, num, bal, accType);
		}
		else if(Save) {
			String accType = "savings";
			System.out.println("\nEnter the Account Interest Rate:");
			double ir = input.nextDouble();
			
			bank.addNewSavingsAccount(name, num, bal, accType,ir);
			bank.addNewAccount(name, num, bal, accType);
		}
		postOption();
	}//Close option1
	
	public static void option2() throws IOException { //Read accounts data
		bank.printAllAccounts();
		postOption();
	}//Close option2
	
	public static void option3() throws IOException { //Retrieve specific account's data based on account number
		System.out.println("\nEnter Account Number(5-digit number):");
		int num = input.nextInt();
		System.out.println(bank.printIsAccountNumber(num));
		postOption();
	}//Close option3

	public static void option4() throws IOException { //Create reversed accounts data file
		bank.reversedAccountsFile();
		postOption();
	}//Close option4
		
	public static void postOption() throws IOException {
		int postOption;
			
		do {
			System.out.println("\nEnter an option number:");
			System.out.println("1. Reload Data\n\r-1. Exit Program");
			postOption = input.nextInt();
		}while(postOption != 1 && postOption != -1);
			
		if(postOption == 1) {
			menu();
		}
		else {
			System.out.println("\nBye!"); input.close(); System.exit(1);
		}
	}
		
}

/*
* Name: [Sleepy Clown]
* CIS 265: Assignment #4
* Description: Create a program to search a text file and create checking and 
* 				savings accounts based on the file contents.
*/

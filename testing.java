package VanceCIS265AS4;

import java.io. *;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testing {
	public static void main(String[] args) throws IOException {
		ArrayList<Accounts> list = new ArrayList<>();
		ArrayList<checkingAccounts> clist = new ArrayList<>();
		ArrayList<savingsAccounts> slist = new ArrayList<>();
		Banks bank = new Banks("Bank_Name");
		
		List<String> tokens = bank.addAccounts();
		for(int i = 0; i < tokens.size(); i++) {
			System.out.println(tokens.get(i));
		}
	}
}

class Banks {
	private ArrayList<Accounts> list = new ArrayList<>();
	private ArrayList<checkingAccounts> clist = new ArrayList<>();
	private ArrayList<savingsAccounts> slist = new ArrayList<>();
	private String bankName;
	
	Banks(String bankName){
		this.bankName = bankName;
	}
	
	public String getBankName() {
		return this.bankName;
	}
	
	public List<String> addAccounts() throws IOException{
		String file = ("C:\\Users\\rhian\\eclipse-workspace\\VanceCIS265Assignments\\src\\VanceCIS265AS4\\");
		String[] args = new String[] {"accounts.txt"};
		
		if (args.length != 1) {
			System. out.println("Usage: java Package textFile");
		}
		
		File sourceFile = new File(file+args[0]);

		Scanner input = new Scanner(sourceFile);
		
		String line = "";
		String name = "";
		int num = 0;
		double bal = 0.0;
		String accType = "other";
		int cl = 0;
		double ir = 0.0;
		List<String> link = new ArrayList<>();
		String[] tokens = new String[] {};
		
		if (sourceFile.exists()) { 
			while(input.hasNext()) {
				line = input.nextLine();
				tokens = line.split(",");
				
				name = tokens[0];
				
				if(isInteger(tokens[1]))
					num = Integer.valueOf(tokens[1]);
				
				if(isDouble(tokens[2]))
					bal = Double.valueOf(tokens[2]);
				
				accType = tokens[3].toString();
				
				int h = tokens.length;
				
				if(h == 5) {
					accType = tokens[3];
					
					if(isInteger(tokens[4])) {
						cl = Integer.valueOf(tokens[4]);
						clist.add(new checkingAccounts(name, num, bal, accType, cl));
					}
					else if(isDouble(tokens[4])) {
						ir = Double.valueOf(tokens[4]);
						slist.add(new savingsAccounts(name, num, bal, accType, ir));
					}
				}//close if(h == 5)
				
				else {
					if(h < 5){
						accType = tokens[3];
						
						if(accType.equals("checking")) {
							clist.add(new checkingAccounts(name, num, bal, accType, cl));
						}
						else if(accType.equals("savings")) {
							slist.add(new savingsAccounts(name, num, bal, accType, ir));
						}
					}//close else if(h < 5)
					else {
						accType = tokens[4];
						
						if(isInteger(tokens[5])) {
							cl = Integer.valueOf(tokens[5]);
							clist.add(new checkingAccounts(name, num, bal, accType, cl));
						}
						else if(isDouble(tokens[5])) {
							ir = Double.valueOf(tokens[5]);
							slist.add(new savingsAccounts(name, num, bal, accType, ir));
						}
					}//close else
				}
				
				
				list.add(new Accounts(name, num, bal, accType));
			}
		}
		for(int i = 0; i < list.size(); i++) {
			link = clist.get(i).getAccountInfo();
			return link;
		}
		return link;
	}//close addAccounts
	
	public int getNumberOfAccounts() {
		return list.size();
	}//close getNumberOfAccounts
	public int getNumberOfCheckingAccounts() {
		return clist.size();
	}//close getNumberOfCheckingAccounts
	public int getNumberOfSavingsAccounts() {
		return slist.size();
	}//close getNumberOfCheckingAccounts
	
	public boolean isAccountNumber(int num) {
		boolean isNum = false;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getNumber() == num) {
				isNum = true;
			}
		}
		return isNum;
	}//close isAccountNumber

	public String printIsAccountNumber(int num) {
		String response = "";
		if(isAccountNumber(num)) {
			for(int i = 0; i < list.size(); i++) {
				if(clist.get(i).getNumber() == num) {
					System.out.println("\nAccount Name         Number (5-digit number)         Balance         Check Limit");
					response = (clist.get(i).printAccountString());
					return response;
				}
				else if(slist.get(i).getNumber() == num) {
					System.out.println("\nAccount Name         Number (5-digit number)         Balance         Interest Rate");
					response = (slist.get(i).printAccountString());
					return response;
				}
			}
		}
		else {
			response = "Account Number not recognized";
			return response;
		}
		return response;
	}//close printIsAccountNumber
	
	public void printAllAccounts() throws IOException{
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getAccountType().equals("checking"))
				System.out.println(clist.get(i).printAccountString());
				
			else if(list.get(i).getAccountType().equals("savings"))
				System.out.println(slist.get(i).printAccountString());
		}
	}//close printAllAccounts
	
	public void reversedAccountsFile() throws IOException{
		String file = ("C:\\Users\\rhian\\eclipse-workspace\\VanceCIS265Assignments\\bin\\VanceCIS265AS4\\");
		
		String[] args = new String[]{"accounts.txt", "accounts_reversed"};
		
		if (args.length != 2) {
			System. out.println("Usage: java ReplaceText sourceFile targetFile oldStr newStr");
			System.exit(1);
		}
		
		File sourceFile = new File(file+args[0]);
		if (!sourceFile.exists()) {
			System.out.println("Source file " + args[0] + " does not exist");
//			sourceFile.createNewFile();
//			try (PrintWriter output = new PrintWriter(sourceFile)) {
//				output.println(args[2]);
//			}
//			System.out.println(args[2]);
//			System.exit(2);
		}
		
		File targetFile = new File(file+args[1]);
		if (targetFile.exists()) {
			System.out.println("Target file " + args[1] + " already exists");
			
			try (Scanner input = new Scanner (sourceFile); PrintWriter output = new PrintWriter(targetFile);) {
				targetFile.createNewFile();
				List<String> s2 = new ArrayList<>();
				
				while (input.hasNext()) {
					String sl = input.nextLine();
					s2.add(sl);
				}
				
				for(int i = s2.size()-1; i >= 0; i--) {
					output.println(s2.get(i));
					System.out.println(s2.get(i));
					}
			}
			System.exit(3);
		}
		else {
			try (Scanner input = new Scanner (sourceFile); PrintWriter output = new PrintWriter(targetFile);) {
				targetFile.createNewFile();
				List<String> s2 = new ArrayList<>();
				
				while (input.hasNext()) {
					String sl = input.nextLine();
					s2.add(sl);
				}
				for(int i = s2.size()-1; i >= 0; i--) {
					output.println(s2.get(i));
					System.out.println(s2.get(i));
				}
			}
		}
	}//close reversedAccountsFile
	
	public boolean isDouble(String obj) { 
		 try { 
		   Double.valueOf(obj); 
		 } 
		 catch (Exception ex){ // Not a valid double value 
		   return (false); 
		 } 
		 return (true); 
	}//close isDouble
	
	public boolean isInteger(String obj) { 
		 try { 
		   Integer.valueOf(obj); 
		 } 
		 catch (Exception ex){ // Not a valid integer value 
		   return (false); 
		 } 
		 return (true); 
	}//close isInteger
}

class Accounts {
	public int Number;
	public double Balance;
	public String Name;
	public String AccountType;
	
	Accounts(){}
	
	Accounts(String name, int num, double bal, String accType) {
		this.Name = name;
		this.Number = num;
		this.Balance = bal;
		this.AccountType = accType;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	
	public void setNumber(int num) {
		this.Number = num;
	}
	
	public void setBalance(double bal) {
		this.Balance = bal;
	}
	
	public void setAccountType(String accType) {
		this.AccountType = accType.toLowerCase();
	}
	
	public String getName() {
		return Name;
	}
	
	public int getNumber() {
		return Number;
	}
	
	public double getBalance() {
		return Balance;
	}
	
	public String getAccountType() {
		return AccountType;
	}
	
	public List<String> getAccountInfo() {
		List<String> info = new ArrayList<>();
		info.add("Name: "+getName());
		info.add("Number: "+Integer.toString(getNumber()));
		info.add("Balance: "+Double.toString(getBalance()));
		info.add("Account Type: "+getAccountType());
		return info;
	}
	
	public void printAccount(PrintWriter output) throws IOException{
		java.io.File file = new java.io.File("C:\\Users\\rhian\\eclipse-workspace\\VanceCIS265Assignments\\bin\\VanceCIS265AS4\\accounts_reversed.txt");
		output = new PrintWriter(file);
		if (file.exists()) { 
			try(PrintWriter write = output){
				output.println(getName()+","+getNumber()+","+getBalance()+","+getAccountType());
			}
		}
	}//close printAccount(PrintWriter)
	
	public String printAccountString(){
		String printing = (getName()+", #"+getNumber()+", $"+getBalance()+", "+getAccountType());	
        return printing;
	}//close printAccount
}

class checkingAccounts extends Accounts {
	public int CheckLimit;
	
	checkingAccounts(){}
	
	checkingAccounts(String name, int num, double bal, String accType, int cl) {
		super.Name = name;
		super.Number = num;
		super.Balance = bal;
		this.CheckLimit = cl;
	}
	
	public void setName(String name) {
		super.Name = name;
	}
	
	public void setNumber(int num) {
		super.Number = num;
	}
	
	public void setCheck(int lim) {
		this.CheckLimit = lim;
	}

	public void setBalance(double bal) {
		super.Balance = bal;
	}
	
	public void setAccountType(String accType) {
		this.AccountType = accType.toLowerCase();
	}
	
	public String getName() {
		return Name;
	}
	
	public int getNumber() {
		return Number;
	}
	
	public double getBalance() {
		return Balance;
	}
	
	public String getAccountType() {
		return AccountType;
	}
	
	public int getCheck() {
		return CheckLimit;
	}
	
	public List<String> getAccountInfo() {
		List<String> info = new ArrayList<>();
		info.add("Name: "+getName());
		info.add("Number: "+Integer.toString(getNumber()));
		info.add("Balance: "+Double.toString(getBalance()));
		info.add("Account Type: "+getAccountType());
		info.add("Check Limit: "+Integer.toString(getCheck()));
		return info;
	}
	
	public void printAccount(PrintWriter output) throws IOException{
		java.io.File file = new java.io.File("C:\\Users\\rhian\\eclipse-workspace\\VanceCIS265Assignments\\bin\\VanceCIS265AS4\\accounts_reversed.txt");
		output = new PrintWriter(file);
		if (file.exists()) { 
			try(PrintWriter write = output){
				output.println(getName()+","+getNumber()+","+getBalance()+","+getAccountType()+","+getCheck());
			}
		}
	}//close printAccount(PrintWriter)
	
	public String printAccountString(){
		String printing = (getName()+", #"+getNumber()+", $"+getBalance()+","+getAccountType()+","+getCheck());	
        return printing;
	}//close printAccount
}

class savingsAccounts extends Accounts{
	public double Interest;
	
	savingsAccounts(){}
	
	savingsAccounts(String name, int num, double bal, String accType, double intr) {
		super.Name = name;
		super.Number = num;
		super.Balance = bal;
		this.Interest = intr;
	}
	
	public void setName(String name) {
		super.Name = name;
	}
	
	public void setNumber(int num) {
		super.Number = num;
	}
	
	public void setInterest(double intr) {
		this.Interest = intr;
	}

	public void setBalance(double bal) {
		super.Balance = bal;
	}
	
	public void setAccountType(String accType) {
		this.AccountType = accType.toLowerCase();
	}
	
	public String getName() {
		return Name;
	}
	
	public int getNumber() {
		return Number;
	}
	
	public double getBalance() {
		return Balance;
	}
	
	public String getAccountType() {
		return AccountType;
	}
	
	public double getInterest() {
		return Interest;
	}
	
	public List<String> getAccountInfo() {
		List<String> info = new ArrayList<>();
		info.add("Name: "+getName());
		info.add("Number: "+Integer.toString(getNumber()));
		info.add("Balance: "+Double.toString(getBalance()));
		info.add("Account Type: "+getAccountType());
		info.add("Interest Rate: "+Double.toString(getInterest()));
		return info;
	}
	
	public void printAccount(PrintWriter output) throws IOException{
		java.io.File file = new java.io.File("C:\\Users\\rhian\\eclipse-workspace\\VanceCIS265Assignments\\bin\\VanceCIS265AS4\\accounts_reversed.txt");
		output = new PrintWriter(file);
		if (file.exists()) { 
			try(PrintWriter write = output){
				output.println(getName()+","+getNumber()+","+getBalance()+","+getAccountType()+","+getInterest());
			}
		}
	}//close printAccount(PrintWriter)
	
	public String printAccountString(){
		String printing = (getName()+", #"+getNumber()+", $"+getBalance()+","+getAccountType()+","+getInterest()+"%");	
        return printing;
	}//close printAccount
}

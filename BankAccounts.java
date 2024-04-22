import java.io. *;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankAccounts {
	private ArrayList<Account> list = new ArrayList<>();
	private ArrayList<checkingAccount> clist = new ArrayList<>();
	private ArrayList<savingsAccount> slist = new ArrayList<>();
	private String bankName;
	
	BankAccounts(String bankName){
		this.bankName = bankName;
	}
	
	public String getBankName() {
		return this.bankName;
	}
	
	public void addNewAccount(String name, int num, double bal, String accType) throws IOException {
		list.add(new Account(name, num, bal, accType));
		
		java.io.File file = new java.io.File(System.getProperty("java.class.path")+"\\"+BankAccounts.class.getPackageName()+"\\");
		String[] args = new String[] {"accounts.txt"};
		
		if (args.length != 1) {
			System. out.println("Usage: java Package textFile");
		}
		
		if (file.exists()) {
			try(PrintWriter output = new PrintWriter(file+args[0])){
				int i = list.size()-1;
				list.get(i).printAccount(output); 
				output.close();
			}
		}
	}
	
	public void addNewCheckingAccount(String name, int num, double bal, String accType, int lim) throws IOException {
		clist.add(new checkingAccount(name, num, bal, accType, lim));
		
		java.io.File file = new java.io.File(System.getProperty("java.class.path")+"\\"+BankAccounts.class.getPackageName()+"\\");
		String[] args = new String[] {"accounts.txt"};
		
		if (args.length != 1) {
			System. out.println("Usage: java Package textFile");
		}
		
		if (file.exists()) {
			try(PrintWriter output = new PrintWriter(file+args[0]);){
				int i = clist.size()-1;
				clist.get(i).printAccount(output);
				output.close();
			}
		}
	}
	
	public void addNewSavingsAccount(String name, int num, double bal, String accType, double intr) throws IOException {
		slist.add(new savingsAccount(name, num, bal, accType, intr));
		
		java.io.File file = new java.io.File(System.getProperty("java.class.path")+"\\"+BankAccounts.class.getPackageName()+"\\");
		String[] args = new String[] {"accounts.txt"};
		
		if (args.length != 1) {
			System. out.println("Usage: java Package textFile");
		}
		
		if (file.exists()) {
			try(PrintWriter output = new PrintWriter(file+args[0]);){
				int i = slist.size()-1;
				slist.get(i).printAccount(output); 
				output.close();
			}
		}
	}
	
	public void addAccounts() throws IOException{
		String file = (System.getProperty("java.class.path")+"\\"+BankAccounts.class.getPackageName()+"\\");
		String[] args = new String[] {"accounts.txt"};
		
		if (args.length != 1) {
			System. out.println("Usage: java Package textFile");
		}
		
		File file1 = new File(file+args[0]);

		Scanner input = new Scanner(file1);
		
		String line = "";
		String name;
		int num;
		double bal;
		String accType;
		int cl;
		double ir;
		
		if (file1.exists()) { 
			do {
				line = input.nextLine();
				String[] tokens = line.split(",");
				name = tokens[0];
				num = Integer.valueOf(tokens[1]);
				bal = Double.valueOf(tokens[2]);
				accType = tokens[3];
				
				if(accType.equals("checking")) {
					cl = Integer.valueOf(tokens[4]);
					clist.add(new checkingAccount(name, num, bal, accType, cl));
				}
				else if(accType.equals("savings")) {
					ir = Double.valueOf(tokens[4]);
					slist.add(new savingsAccount(name, num, bal, accType, ir));
				}
				
				list.add(new Account(name, num, bal, accType));
			} while(input.hasNext());
		}
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
	}
	
	public void reversedAccountsFile() throws IOException{
		String file = (System.getProperty("java.class.path")+"\\"+BankAccounts.class.getPackageName()+"\\");
		
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
	}
}

class Account {
	public int Number;
	public double Balance;
	public String Name;
	public String AccountType;
	
	Account(){}
	
	Account(String name, int num, double bal, String accType) {
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
	
	public void printAccount(PrintWriter output) throws IOException{
		java.io.File file = new java.io.File(System.getProperty("java.class.path")+"\\"+Account.class.getPackageName()+"\\accounts.txt");
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

class checkingAccount extends Account {
	public int CheckLimit;
	
	checkingAccount(){}
	
	checkingAccount(String name, int num, double bal, String accType, int cl) {
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
	
	public void printAccount(PrintWriter output) throws IOException{
		java.io.File file = new java.io.File(System.getProperty("java.class.path")+"\\"+checkingsAccount.class.getPackageName()+"\\accounts.txt");
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

class savingsAccount extends Account{
	public double Interest;
	
	savingsAccount(){}
	
	savingsAccount(String name, int num, double bal, String accType, double intr) {
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
	
	public void printAccount(PrintWriter output) throws IOException{
		java.io.File file = new java.io.File(System.getProperty("java.class.path")+"\\"+savingsAccount.class.getPackageName()+"\\accounts.txt");
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

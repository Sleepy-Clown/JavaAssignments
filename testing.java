package VanceCIS265AS4;

import java.io. *;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class testing {
	public static void main(String[] args) throws Exception {
		DoTheThing dtt = new DoTheThing();
		List<Information> list = dtt.addFileInformation();
		
		System.out.println(dtt.fileReader());
		
	}
}

class DoTheThing{
	private ArrayList<Information> i1 = new ArrayList<>();
	
	public List<String> fileReader() throws IOException {
		String file = ("C:\\Users\\rhian\\eclipse-workspace\\VanceCIS265Assignments\\bin\\VanceCIS265AS4\\");
		
		String[] args = new String[]{"accounts.txt", "accounts_reversed.txt"};
		
		if (args.length != 2) {
			System. out.println("Usage: java ReplaceText sourceFile targetFile");
			System.exit(1);
		}
		String[] s2 = new String[] {};
		File sourceFile = new File(file+args[0]);
		if (!sourceFile.exists()) {
			System.out.println("Source file " + args[0] + " does not exist");
			System.exit(2);
		}
		List<String> list = new ArrayList<>();
		try (Scanner input = new Scanner (sourceFile);) {
			while (input.hasNext()) {
				
				String sl = input.nextLine();
				s2 = sl.split(",");
				
				/*i1.add(new Information((s2[0]), (Integer.valueOf(s2[1])), (Double.valueOf(s2[2])), (s2[3])));
				 *
				 * if(s2[3].equals("checking")) {Integer.valueOf(s2[4]))} else
				 * if(s2[3].equals("savings")) { IRs.add(Double.valueOf(s2[4])); }
				 */
				
				//list.add(sl);
				list.add("["+sl+"]");
				
				/*
				 * for(int i = 0; i < s2.length; i++) { list.add(s2[i]); }
				 */
			}
			return list;
		}
	}
	
	public List<Information> addFileInformation() throws IOException{
		List<String> names = Names();
		List<Integer> nums = Numbers();
		List<Double> bals = Balances();
		List<String> accTypes = AccountTypes();
		List<Integer> CLs = CheckLimits();
		List<Double> IRs = InterestRates();
		
		for(int i = 0; i < i1.size(); i++) {
			i1.add(new Information(names.get(i), nums.get(i), bals.get(i), accTypes.get(i)));
		}
		return i1;
	}
	
	public List<String> Names() throws IOException{
		List<String> list = fileReader();
		List<String> names = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(i%5 == 0) {
				names.add(list.get(i));
				return names;
			}
			return names;
		}
		return names;
	}
	
	public List<Integer> Numbers() throws IOException{
		List<String> list = fileReader();
		List<Integer> nums = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(i%5 == 1) {
				nums.add(Integer.valueOf(list.get(i)));
				return nums;
			}
			return nums;
		}
		return nums;
	}
	
	public List<Double> Balances() throws IOException{
		List<String> list = fileReader();
		List<Double> bals = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(i%5 == 2) {
				if(!isDouble(list.get(i))) {
					bals.add(0.0);
					return bals;
				}
				else {
					bals.add(Double.valueOf(list.get(i))); 
					return bals;
				}
			}
			return bals;
		}
		return bals;
	}
	
	public List<String> AccountTypes() throws IOException{
		List<String> list = fileReader();
		List<String> accTypes = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(i%5 == 3) {
				accTypes.add(list.get(i));
				return accTypes;
			}
			return accTypes;
		}
		return accTypes;
	}
	
	public List<Integer> CheckLimits() throws IOException{
		List<String> list = fileReader();
		List<Integer> CLs = new ArrayList<>();
		boolean cl = false;
		for(int i = 0; i < list.size(); i++) {
			if(i%5 == 3) {
				if(list.get(i).equals("checking")) {
					cl = true;
				}
			}
			if(cl) {
				if(i%5 == 4) {
					if(!isInteger(list.get(i))) {
						CLs.add(0);
						return CLs;
					}
					else {
						CLs.add(Integer.valueOf(list.get(i))); 
						return CLs;
					}
				}
			}
			return CLs;
		}
		return CLs;
	}
	
	public List<Double> InterestRates() throws IOException{
		List<String> list = fileReader();
		List<Double> IRs = new ArrayList<>();
		boolean ir = false;
		for(int i = 0; i < list.size(); i++) {
			if(i%5 == 3) {
				if(list.get(i).equals("savings")) {
					ir = true;
				}
			}
			if(ir) {
				if(i%5 == 4) {
					if(!isDouble(list.get(i))) {
						IRs.add(0.0);
						return IRs;
					}
					else {
						IRs.add(Double.valueOf(list.get(i))); 
						return IRs;
					}
				}
			}
			return IRs;
		}
		return IRs;
	}
	
	public void printInfo(List<Information> list) throws IOException {
		list = addFileInformation();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).printInformation());
		}
	}
	
	public boolean isDouble(String obj) { 
		 try { 
		   Double.valueOf(obj); 
		 } 
		 catch (Exception ex){ // Not a valid double value 
		   return (false); 
		 } 
		 return (true); 
	} 
	
	public boolean isInteger(String obj) { 
		 try { 
		   Integer.valueOf(obj); 
		 } 
		 catch (Exception ex){ // Not a valid double value 
		   return (false); 
		 } 
		 return (true); 
	} 
}

class Information {
	public int Number;
	public double Balance;
	public String Name;
	public String AccountType;
	
	Information(){}
	
	Information(String name, int num, double bal, String accType) {
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
	
	public String printInformation(){
		String printing = (getName()+", #"+getNumber()+", $"+getBalance()+", "+getAccountType());	
        return printing;
	}//close printAccount
}
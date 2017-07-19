package buyAndSell;

import java.util.HashMap;
import java.util.Vector;

public class Store {
	
	//for running as a java application in testing
	public static void main(String[] args) {
		
	}
	
	//will be in the database later on
	private HashMap<String,String> passwordMap; //key = username, value = password
	private HashMap<String,User> userMap; //key = username, value = User
	private HashMap<Vector<String>,Item> keywordMap; //key = vector of keywords, value = Item
	
	//null if the guest isn't logged in, set when they are
	private User currUser; 
	
	
	public Store(){
		this.passwordMap = new HashMap<String,String>(); 
		this.userMap = new HashMap<String,User>(); 
		setCurrUser(null);
	}
	
	//return boolean of success or failure
	public boolean login(String username, String password) {
		//get the password via the username
		String pword = passwordMap.get(username);
		if (pword == null) {
			return false;
		}
		
		//make sure the password they gave equals the one we have stored
		if (pword.equals(password)) {
			setCurrUser(userMap.get(username));
			return true;
		}
		
		//failure
		return false;
		
	}
	
	//return boolean of success or failure
	public boolean createUser(String fName, String lName, String email, String phoneNum, String username, String password) {
		//user already exists
		if (passwordMap.containsKey(password)) {
			return false;
		}
		
		User u = new User(fName, lName, email, phoneNum, username, password);
		currUser = u;
		return true;
	}

	public boolean addItem(String name, String price, Category category, String quantity) {
		//convert price string to float- if it fails return false
		float pFloat;
		try {
			pFloat = Float.parseFloat(price);
		} catch (NumberFormatException e) {
			return false;
		}		
		
		//convert quantity string to int- if it fails return false
		int qInt; 
		try {
			qInt = Integer.parseInt(quantity);
		} catch (NumberFormatException e) {
			return false;
		}
		
		Item i = new Item(name, pFloat, category, qInt);
		parseKeywords(i);
		
		
		return true;
	}
	
	
	//parses the keywords of an item and adds them to the map (database)
	private void parseKeywords(Item item) {
		
		
		
	}
	
	
	public Vector<Item> search(Vector<String> searchTerms) {
		Vector<Item> results = new Vector<Item>();
		
		
		
		
		
		
		return results;
	}
	
	
	
	
	
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	public User getCurrUser() {
		return currUser;
	}

	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}


}

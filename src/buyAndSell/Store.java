package buyAndSell;

import java.util.HashMap;
import java.util.Vector;

public class Store {
	
	//for running as a java application in testing
	public static void main(String[] args) {
		
		Store s = new Store();
		User u = new User("Brandon", "Holden", "bholden@usc.edu", "978-257-5700", "bholden", "password");
		
		s.setCurrUser(u);

		//add the the maps I have
		passwordMap.put("bholden", "password");
		userMap.put("bholden", u);
		
		
		Item car = new Item("My awesome first car", 10000.99, Category.ELECTRONIC, 1, u);
		Item tv = new Item("really awesome tv", 111.93, Category.ELECTRONIC, 1, u);
		sellItem(car);
		sellItem(tv);
		
		//go through keywords and items associated with it
		for (String keyword: keywordMap.keySet()) {
			System.out.println(keyword);
			Vector<Item> items = keywordMap.get(keyword);
			if (!(items.isEmpty())) {
				System.out.println("\tItems:");
			}
			for (int i = 0; i < items.size(); i++) {
				System.out.println("\t\t"+items.get(i).getName());
			}
		}
		
	}
	
	//will be in the database later on
	private static HashMap<String,String> passwordMap; //key = username, value = password
	private static HashMap<String,User> userMap; //key = username, value = User
	private static Vector<Item> allItems;
	private static HashMap<String,Vector<Item>> keywordMap; //key = keyword, value = vector of Items matching
	
	//null if the guest isn't logged in, set when they are
	private static User currUser; 
	
	
	public Store(){
		this.passwordMap = new HashMap<String,String>(); 
		this.userMap = new HashMap<String,User>(); 
		this.keywordMap = new HashMap<String,Vector<Item>>();
		this.allItems = new Vector<Item>();
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
	
	
	public boolean sellItemFromParam(String name, String price, Category category, String quantity) {
		//convert price string to double- if it fails return false
		double pDouble;
		try {
			pDouble = Double.parseDouble(price);
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
		
		Item i = new Item(name, pDouble, category, qInt, currUser);
		
		//NEED TO ASSOCIATE THIS WITH A USER- CURRENTLY currUser
		currUser.addItemSelling(i);
				
		parseKeywords(i);			
		allItems.add(i);
		
		return true;
	}
	

	
	public static void sellItem(Item i) {
		//NEED TO ASSOCIATE THIS WITH A USER- CURRENTLY currUser
		currUser.addItemSelling(i);
				
		parseKeywords(i);
		allItems.add(i);
	}
	
	
	//parses the keywords of an item and adds them to the map (database)
	private static void parseKeywords(Item item) {
		//get keywords from the title and description
		String name = item.getName();
		String description = item.getDescription();
		String[] nameKeywords = name.split(" ");
		
		
		//KEY NEEDS TO BE LOWERCASE TO MATCH SEARCH
		
		//go through the name, make keyword strings splitting at every non-alpha char, add to map
		String keyword = "";
		for (int i = 0; i < name.length(); i++) {	
			if (Character.isLetter(name.charAt(i))) {
				keyword += name.charAt(i);
			}
			else {
				keyword = keyword.toLowerCase();
				//see if keyword is in the map
				if (keywordMap.containsKey(keyword)) {
					Vector<Item> currentItems = keywordMap.get(keyword);
					currentItems.add(item);
					keywordMap.put(keyword, currentItems);
				}
				else {
					Vector<Item> newItems = new Vector<Item>();
					newItems.add(item);
					keywordMap.put(keyword, newItems);
				}
				keyword = "";
			}
			
			//not recognizing the newline character- manual override to add it
			if (i == name.length()-1) {
				keyword = keyword.toLowerCase();
				//see if keyword is in the map
				if (keywordMap.containsKey(keyword)) {
					Vector<Item> currentItems = keywordMap.get(keyword);
					currentItems.add(item);
					keywordMap.put(keyword, currentItems);
				}
				else {
					Vector<Item> newItems = new Vector<Item>();
					newItems.add(item);
					keywordMap.put(keyword, newItems);
				}
			}
		}

		
		
		
		
		
//		//add the items to the keywordMap based on keyword 
//		for (int i = 0; i < nameKeywords.length; i++) {
//			String keyword = nameKeywords[i].toLowerCase();
//			//see if keyword is in the map
//			if (keywordMap.containsKey(keyword)) {
//				Vector<Item> currentItems = keywordMap.get(keyword);
//				currentItems.add(item);
//				keywordMap.put(keyword, currentItems);
//			}
//			else {
//				Vector<Item> newItems = new Vector<Item>();
//				newItems.add(item);
//				keywordMap.put(keyword, newItems);
//			}
//		}
//		
//		//if there is no description- return
//		if (item.getDescription() == null) {
//			return;
//		}
//		
//		String[] descrptionKeywords = description.split(" ");
//		
//		for (int i = 0; i < descrptionKeywords.length; i++) {
//			String keyword = descrptionKeywords[i].toLowerCase();
//			//see if keyword is in the map
//			if (keywordMap.containsKey(keyword)) {
//				Vector<Item> currentItems = keywordMap.get(keyword);
//				currentItems.add(item);
//				keywordMap.put(keyword, currentItems);
//			}
//			else {
//				Vector<Item> newItems = new Vector<Item>();
//				newItems.add(item);
//				keywordMap.put(keyword, newItems);
//			}
//		}
		
		
	}
	
	
	//search terms must be converted to lower case
	public Vector<Item> search(Vector<String> searchTerms) {
		Vector<Item> results = new Vector<Item>();
		
		//go through all search terms, get all items associated w/keyword, add those to results
		for (int i = 0; i < searchTerms.size(); i++) {
			String term = searchTerms.get(i).toLowerCase();
			if (keywordMap.containsKey(term)) {
				Vector<Item> items = keywordMap.get(term);
				for (int j = 0; j < items.size(); j++) {
					Item item = items.get(j);
					results.add(item);
				}
			}			
		}

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

package buyAndSell;

import java.util.HashMap;
import java.util.Vector;

public class Store {
	
	//will be in the database later on
	static HashMap<String,String> passwordMap; //key = username, value = password
	static HashMap<String,User> userMap; //key = username, value = User
	static Vector<Item> allItems;
	static HashMap<String,Vector<Item>> keywordMap; //key = keyword, value = vector of Items matching
	
	//null if the guest isn't logged in, set when they are
	//I THINK THIS WILL BECOME THE PRIMARY KEY IN THE DATABASE
	private static User currUser;
		
	public Store(){
		Store.passwordMap = new HashMap<String,String>(); 
		Store.userMap = new HashMap<String,User>(); 
		Store.keywordMap = new HashMap<String,Vector<Item>>();
		Store.allItems = new Vector<Item>();
		setCurrUser(null);
	}
	
	
	//return boolean of success or failure- just query the database with usernames/passwords/uid
	public static boolean login(String username, String password) {
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
		passwordMap.put(username, password);
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
		
		//parse the name and optional description
		parseStringAndAddToKeywords(name, item);
		parseStringAndAddToKeywords(description, item);
		
	}
	
	private static void parseStringAndAddToKeywords(String toParse, Item item) {
		//KEY NEEDS TO BE LOWERCASE TO MATCH SEARCH
		
		if (toParse == null) {
			return;
		}
		
		//go through the name, make keyword strings splitting at every non-alpha char, add to map
		String keyword = "";
		for (int i = 0; i < toParse.length(); i++) {	
			if (Character.isLetter(toParse.charAt(i))) {
				keyword += toParse.charAt(i);
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
			if (i == toParse.length()-1) {
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
	}
	
	
	//search terms must be converted to lower case
	public static Vector<Item> search(String searchTerm) {
		Vector<Item> results = new Vector<Item>();
		
		String[] searchTerms = searchTerm.split(" ");
		
		//go through all search terms, get all items associated w/keyword, add those to results
		for (int i = 0; i < searchTerms.length; i++) {
			String term = searchTerms[i].toLowerCase();
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

	public static void setCurrUser(User currUser) {
		Store.currUser = currUser;
	}
	
	public static HashMap<String, String> getPasswordMap() {
		return passwordMap;
	}

	public static void setPasswordMap(HashMap<String, String> passwordMap) {
		Store.passwordMap = passwordMap;
	}

	public static HashMap<String, User> getUserMap() {
		return userMap;
	}

	public static void setUserMap(HashMap<String, User> userMap) {
		Store.userMap = userMap;
	}

	public static Vector<Item> getAllItems() {
		return allItems;
	}

	public static void setAllItems(Vector<Item> allItems) {
		Store.allItems = allItems;
	}

	public static HashMap<String, Vector<Item>> getKeywordMap() {
		return keywordMap;
	}

	public static void setKeywordMap(HashMap<String, Vector<Item>> keywordMap) {
		Store.keywordMap = keywordMap;
	}


}

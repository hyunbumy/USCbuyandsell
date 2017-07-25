package buyAndSell;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Vector;

import com.google.common.hash.Hashing;

public class StoreDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	private static User currUser = null;
		
	public StoreDatabase(){

		//establish database connection
		
		setCurrUser(null);

	}
	
	//return boolean of success or failure
	public static boolean login(String username, String password) {
		
		//query db
		
		//make sure the password they gave equals the one we have stored
		// Hash the password using SHA256 and salt
		password = Hashing.sha256().
				hashString(password+"salt", StandardCharsets.UTF_8).toString();
		
		//make sure username doesn't already exist, if so, return false
		//store the current userID- use that to create getPhoneNum() methods etc. 
		//OR store the ResultSet and use that to get the values to not query
		//OR instantiate a User object and use those methods and create a saveUser method
		
		
		//failure
		return false;
		
	}
	
	//return boolean of success or failure
	public static boolean createUser(String fName, String lName, String email, 
			String phoneNum, String username, String password, String image) {
		
		
		
		// Use hashing with salt for password when creating a new user
		password = Hashing.sha256().
				hashString(password+"salt", StandardCharsets.UTF_8).toString();
		
		//check to see if they exist in the UserTable, if not, add them
		//if they do, return false for an error message
		
		
		return true;
	}
	
	

	
	public static void sellItem(Item i) {
		
		//NEED THIS TO RETURN THE KEYWORDS	
		//or I could just add to the database from there idk yet
		parseKeywords(i);
		
		//add item to ItemTable and keywords to KeywordTable
		
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
//		String keyword = "";
//		for (int i = 0; i < toParse.length(); i++) {	
//			if (Character.isLetter(toParse.charAt(i))) {
//				keyword += toParse.charAt(i);
//			}
//			else {
//				keyword = keyword.toLowerCase();
//				//see if keyword is in the map
//				if (keywordMap.containsKey(keyword)) {
//					Vector<Item> currentItems = keywordMap.get(keyword);
//					currentItems.add(item);
//					keywordMap.put(keyword, currentItems);
//				}
//				else {
//					Vector<Item> newItems = new Vector<Item>();
//					newItems.add(item);
//					keywordMap.put(keyword, newItems);
//				}
//				keyword = "";
//			}
//			
//			//not recognizing the newline character- manual override to add it
//			if (i == toParse.length()-1) {
//				keyword = keyword.toLowerCase();
//				//see if keyword is in the map
//				if (keywordMap.containsKey(keyword)) {
//					Vector<Item> currentItems = keywordMap.get(keyword);
//					currentItems.add(item);
//					keywordMap.put(keyword, currentItems);
//				}
//				else {
//					Vector<Item> newItems = new Vector<Item>();
//					newItems.add(item);
//					keywordMap.put(keyword, newItems);
//				}
//			}
//		}
	}
	
	
	//search terms must be converted to lower case
	public static Vector<Item> search(String searchTerm) {
		Vector<Item> results = new Vector<Item>();
		
		String[] searchTerms = searchTerm.split(" ");
		
		//query KeywordTable

		return results;
	}

	
	//the only getter the rest of the program should need
	public static User getCurrUser() {
		return currUser;
	}

	//please leave private
	private static void setCurrUser(User currUser) {
		StoreDatabase.currUser = currUser;
	}

}

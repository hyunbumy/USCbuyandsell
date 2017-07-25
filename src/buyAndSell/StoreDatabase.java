package buyAndSell;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import com.google.common.hash.Hashing;

public class StoreDatabase {

	private static int currUserId;
		
	public StoreDatabase(){
		currUserId = -1;
	}
	
	// Establish DB connection
	private static Statement connect()
	{
		//establish database connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/USCbuyandsell?user=root&password=root");
			Statement st = conn.createStatement();
			System.out.println(st);
			return st;
		} catch(SQLException sqle) {
			System.out.println("sqle: "+sqle.getMessage());
		} catch(ClassNotFoundException cnfe) {
			System.out.println("cnfe: "+cnfe.getMessage());
		}
		return null;
	}
	
	//return boolean of success or failure
	public static boolean login(String username, String password) {
		
		//make sure the password they gave equals the one we have stored
		// Hash the password using SHA256 and salt
		password = Hashing.sha256().
				hashString(password+"salt", StandardCharsets.UTF_8).toString();
		
		// Query
		Statement st = connect();
		ResultSet rs;
		System.out.println(st);
		try {
			rs = st.executeQuery("SELECT userID FROM UserTable WHERE "
					+ "uname=\'"+username+"\' AND pword=\'"+password+"\';");
			// There should be either one or no result row
			if(rs.next()) {
				System.out.println(rs.getInt("userID"));
				// If true, there was a match, therefore correct login
				currUserId = rs.getInt("userID");
				System.out.println(rs.getInt("userID"));
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
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
		Statement st = connect();
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT userID FROM UserTable WHERE "
					+ "uname=\'"+username+"\';");
			// There should be either one or no result row
			if(!rs.next()) {
				String query = "INSERT INTO UserTable(uname, pword, fname,lname,email,phoneNum,image)";
				query += " VALUES(\'"+username+"\',\'"+password+"\',\'"+fName+"\',";
				query += "\'"+lName+"\',\'"+email+"\',\'"+phoneNum+"\',\'"+image+"\');";
				System.out.println(query);
				System.out.println(st.executeUpdate(query));
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return false;
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
	
	
	
	// Getters and Setters
	public int getCurrUserId()
	{
		return currUserId;
	}
	public void setCurrUserId(int id)
	{
		currUserId = id;
	}

}

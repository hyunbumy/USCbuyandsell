package buyAndSell;

import static java.sql.DriverManager.getConnection;

import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.google.common.hash.Hashing;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class StoreDatabase {

	public static void main(String[] args) {
		StoreDatabase s = new StoreDatabase();
		login("jmilly", "lab1");
	}
	
	private static User currUser = null;
		
	public StoreDatabase (){
		//set currUser to null
		setCurrUser(null);
	}
	
	// Establish DB connection
	private static Statement connect() {
		//establish database connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/USCbuyandsell?user=root&password=root");
			Statement st = (Statement) conn.createStatement();
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
		//query db
		Statement st = connect();
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT uname, pword, fname,lname,email,phoneNum,image,userID FROM UserTable"
					+ " WHERE " + "uname=\'"+username+"\' AND pword=\'"+password+"\';");
			// There should be either one or no result row
			if(rs.next()) {
				// If true, there was a match, therefore correct login
				String uname = rs.getString("uname");
				String pword = rs.getString("pword");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phoneNum");
				String image = rs.getString("image");
				int userID = rs.getInt("userID");
				
//				System.out.println("Username: "+ uname);
//				System.out.println("Password: "+ pword);
//				System.out.println("First Name: "+ fname);
//				System.out.println("Last Name: "+ lname);
//				System.out.println("Email: "+ email);
//				System.out.println("Phone Number: "+ phoneNum);
//				System.out.println("Image: "+ image);
//				System.out.println("UserID: "+ userID);
				
				User u = new User(fname, lname, email, phoneNum, uname, pword, userID);
				currUser = u;
				
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Login failure: " + e.getMessage());
			return false;
		}	
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

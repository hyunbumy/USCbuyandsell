package buyAndSell;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import com.google.common.hash.Hashing;

public class StoreDatabase {

	private static User currUser;
		
	public StoreDatabase(){
		currUser = null;
	}
	
	// Establish DB connection
	private static Statement connect()
	{
		//establish database connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://buyandselldb.cphsc4421sco.us-east-2.rds.amazonaws.com/USCbuyandsell?user=root&password=uscbuyandsell");
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
		//hash password
		password = Hashing.sha256().hashString(password+"salt", StandardCharsets.UTF_8).toString();
		
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
				System.out.println(userID);
				
//				System.out.println("Username: "+ uname);
//				System.out.println("Password: "+ pword);
//				System.out.println("First Name: "+ fname);
//				System.out.println("Last Name: "+ lname);
//				System.out.println("Email: "+ email);
//				System.out.println("Phone Number: "+ phoneNum);
//				System.out.println("Image: "+ image);
//				System.out.println("UserID: "+ userID);
				
				User u = new User(fname, lname, email, phoneNum, uname, userID);
				StoreDatabase.currUser = u;
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Login failure: " + e.getMessage());
			return false;
		}	
		
		//failure
		return false;
		
	}
	
	
	/*
	 * IMPORTANT:
	 * image can be null, or empty string the rest must have values
	 * 
	 */
	//return boolean of success or failure
	public static boolean createUser(String username, String password, String fName, String lName, String email, 
			String phoneNum, String image) {
		
		if (image == null || image.equals("")) {
			image = null;
		}
		
		// Use hashing with salt for password when creating a new user
		password = Hashing.sha256().
				hashString(password+"salt", StandardCharsets.UTF_8).toString();
		
		//check to see if they exist in the UserTable, if not, add them
		//if they do, return false for an error message	
		//query db
		Statement st = connect();
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT uname FROM UserTable" + " WHERE " + "uname=\'"+username+"\';");
			//if there is a result that means the user already exists 
			if((rs.next())) {
				// If true, there was a match, therefore correct login
				System.out.println("user already exists");
				return false;
			}
			else {
				String query = "INSERT INTO UserTable(uname,pword,fname,lname,email,phoneNum,rating,image)\n";
				query += "VALUES (\'"+ username + "\'," + "\'"+password+"\'," + "\'"+fName+"\'," 
						+ "\'"+lName+"\'," +"\'"+ email+"\'," + "\'"+phoneNum+"\',"+ 0.0 +"," +"\'"+ image +"\');";
				st.executeUpdate(query);
				
				rs = st.executeQuery("SELECT uname, pword, fname,lname,email,phoneNum,rating,image,userID FROM UserTable"
						+ " WHERE " + "uname=\'"+username+"\';");
				if (rs.next()) {
					String unameDB = rs.getString("uname");
					String pwordDB = rs.getString("pword");
					String fnameDB = rs.getString("fname");
					String lnameDB = rs.getString("lname");
					String emailDB = rs.getString("email");
					String phoneNumDB = rs.getString("phoneNum");
					String imageDB = rs.getString("image");
					int userID = rs.getInt("userID");
					
//					System.out.println("Username: "+ unameDB);
//					System.out.println("Password: "+ pwordDB);
//					System.out.println("First Name: "+ fnameDB);
//					System.out.println("Last Name: "+ lnameDB);
//					System.out.println("Email: "+ emailDB);
//					System.out.println("Phone Number: "+ phoneNumDB);
//					System.out.println("Image: "+ imageDB);
//					System.out.println("UserID: "+ userID);
					
					User u = new User(fName, lName, email, phoneNum, username, userID);
					StoreDatabase.currUser = u;
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Create User failure: " + e.getMessage());
			return false;
		}	
		
		//failure
		return false;
	}	
	
	/*
	 * IMPORTANT:
	 * description and image can be null or empty string, the rest must have values
	 * 
	 */
	public static void sellItem(String name, float price, Category category, int quantity, String description, String image) {
		//query db and add Item to ItemsTable, and keywords to Keyword table
		Statement st = connect();
		ResultSet rs;
		int sellingUserID = StoreDatabase.currUser.getUserID();

		//making sure db recognizes "" as null
		if (description == null || description.equals("")) {
			description = null;
		}
		if (image == null || image.equals("")) {
			image = null;
		}
		
		try {
			String query = "INSERT INTO ItemsTable(sellingUser,title,price,category,quantity,description,image)\n";
			query += "VALUES (" + sellingUserID + "," + "\'"+name+"\'," + +price+"," + "\'"+category
					+"\'," + quantity + "," + "\'"+description+"\'," +"\'"+ image +"\');";
			st.executeUpdate(query);
			
			//get the itemID that was generated
			rs = st.executeQuery("SELECT itemID FROM ItemsTable WHERE " + "title=\'"+name+
					"\' AND sellingUser="+sellingUserID+ " AND description=\'"+description+"\';");
			
			if (rs.next()) {
				int itemID = rs.getInt("itemID");
				System.out.println(itemID);
				//get the keywords and add those to db
				Vector<String> keywords = new Vector<String>();
				parseKeywords(name, keywords);
				parseKeywords(description, keywords);
				for (int j = 0; j < keywords.size(); j++) {
					//add to Keywords
					String k = keywords.get(j);
					String dbStr = "INSERT INTO KeywordTable(keyword,itemID)\n";
					dbStr += "VALUES (\'"+ k + "\'," + itemID +");";
					st.executeUpdate(dbStr);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Sell item failure: " + e.getMessage());
		}			
		
	}
	
	
	private static Vector<String> parseKeywords(String toParse, Vector<String> keywords) {	
		if (toParse == null) {
			return keywords;
		}
		
		//go through the name, make keyword strings splitting at every non-alpha char, add to map
		String keyword = "";
		for (int i = 0; i < toParse.length(); i++) {	
			if ((Character.isLetter(toParse.charAt(i))) || (Character.isDigit(toParse.charAt(i)))) {
				keyword += toParse.charAt(i);
			}
			else {
				//means have a keyword, add to vector and reset
				if (!(keyword.equals(""))) {
					keywords.add(keyword);
					keyword = "";
				}
			}	
			//not recognizing the newline character- manual override to add it
			if (i == toParse.length()-1) {
				if (!(keyword.equals(""))) {
					keywords.add(keyword);
					keyword = "";
				}
			}
		}
		return keywords;
	}
	
	
	//search terms must be converted to lower case
	public static Vector<Item> search(String searchTerm, String category) {
		//convert toLower for search terms and db keywords
		Vector<Item> results = new Vector<Item>();
		
		String[] searchTerms = searchTerm.split(" ");
		category = category.toUpperCase();
		
		//query KeywordTable
		Statement st = connect();
		ResultSet rs;
		try {
			//get the itemIDs for each search term
			HashSet<Integer> ids = new HashSet<>();
			for (int i = 0; i < searchTerms.length; i++) {
				String keyword = searchTerms[i];
				// If no search term, return all items regardless the keyword
				if (searchTerm.equals(""))
					rs = st.executeQuery("SELECT itemID FROM KeywordTable;");
				else
					rs = st.executeQuery("SELECT itemID FROM KeywordTable WHERE keyword=\'"+keyword+ "\';");	
				//go through itemIDs to get items from ItemsTable
				while (rs.next()) {
					int itemID = rs.getInt("itemID");
					//need to get all the Item info and instantiate Item
					ids.add(itemID);
				}
			}
			
			// Instantiate items with the same category
			Iterator iter = ids.iterator();
			while(iter.hasNext())
			{
				int currId = (int) iter.next();
				// If all category
				if (category.equals("ALL"))
					rs = st.executeQuery("SELECT * FROM ItemsTable WHERE itemID="+currId+";"); 
				else
					rs = st.executeQuery("SELECT * FROM ItemsTable WHERE itemID="+currId+" AND category=\'"+category+"\';");
				while (rs.next()) {
					String name = rs.getString("title");
					int quantity = rs.getInt("quantity");
					int sellerId = rs.getInt("sellingUser");
					int itemId = rs.getInt("itemID");
					float price = rs.getFloat("price");
					Category cat = Category.valueOf(rs.getString("category"));
					results.add(new Item(name, price, cat, quantity, itemId, sellerId));
				}
			}
			
		
		} catch (SQLException e) {
			System.out.println("Search failure: " + e.getMessage());
		}	

		return results;
	}

	
	//the only getter the rest of the program should need
	public static User getCurrUser() {
		return currUser;
	}

	//please leave private
	public static void setCurrUser(User currUser) {
		StoreDatabase.currUser = currUser;
	}
	

}
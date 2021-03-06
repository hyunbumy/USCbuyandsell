package buyAndSell;

import static java.sql.DriverManager.getConnection;

import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import com.google.common.hash.Hashing;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class StoreDatabase {
	
	private static User currUser = null;
	private static Connection conn = null;
	
	// I don't think this constructor is actually being called
	public StoreDatabase (){
		//set currUser to null
		StoreDatabase.setCurrUser(null);
	}
	
	// Establish DB connection
	private static Statement connect() {
		//establish database connection
		try {
			if (conn != null)
				conn.close();
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/USCbuyandsell?user=root&password=root");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://buyandselldb.cphsc4421sco.us-east-2.rds.amazonaws.com:3306/USCbuyandsell?user=root&password=uscbuyandsell");
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
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phoneNum");
				String image = rs.getString("image");
				int userID = rs.getInt("userID");
				
				User u = new User(fname, lname, email, phoneNum, uname, userID, image);
				StoreDatabase.currUser = u;
				
				//check the ratings table
				String query = "SELECT rating from UserRatings WHERE userID="+userID;
				Vector<Integer> ratings = new Vector<Integer>();
				rs = st.executeQuery(query);
				while (rs.next()) {
					int rating = rs.getInt("rating");
					ratings.add(rating);
				}
				//average it
				float total = (float) 0.0;
				float numRatings = (float) ratings.size();
				for (int i = 0; i < ratings.size(); i++) {
					total += (float) ratings.get(i);
				}
				float average = total/numRatings;
				u.setRating(average);
				System.out.println(average);
				
				
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
	public static float getCurrUserRating() {	
		//query db
		Statement st = connect();
		ResultSet rs;
		try {
			//check the ratings table
			String query = "SELECT rating from UserRatings WHERE userID="+currUser.getUserID();
			Vector<Integer> ratings = new Vector<Integer>();
			rs = st.executeQuery(query);
			while (rs.next()) {
				int rating = rs.getInt("rating");
				ratings.add(rating);
			}
			//average it
			float total = (float) 0.0;
			float numRatings = (float) ratings.size();
			for (int i = 0; i < ratings.size(); i++) {
				total += (float) ratings.get(i);
			}
			float average = total/numRatings;
			currUser.setRating(average);
			return average;

		} catch (SQLException e) {
			System.out.println("Login failure: " + e.getMessage());
		}	
			
		return (float) 0.0;
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
			image = "img/default_profile.png";
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
					String fnameDB = rs.getString("fname");
					String lnameDB = rs.getString("lname");
					String emailDB = rs.getString("email");
					String phoneNumDB = rs.getString("phoneNum");
					String imageDB = rs.getString("image");
					int userID = rs.getInt("userID");
					
					User u = new User(fnameDB, lnameDB, emailDB, phoneNumDB, unameDB, userID, imageDB);
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
			image = "img/default_item.jpg";
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
		HashSet<Integer> ids = new HashSet<>();
		try {
			//get the itemIDs for each search term
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
					//need to get all the Item info from ItemsTable and instantiate Item
					ids.add(itemID);
				}
			}
			
			// Instantiate items with the same category
			Iterator iter = ids.iterator();
			while(iter.hasNext()) {
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
					String img = rs.getString("image");
					String desc = rs.getString("description");
					Item i = new Item(name,price,  cat,quantity, itemId, sellerId, img);
					i.setDescription(desc);
					results.add(i);
				}
			}
			
		
		} catch (SQLException e) {
			System.out.println("Search failure: " + e.getMessage());
		}	
		
		return results;
	}

	
	//pass in a userID and get back a User object (or null if id isn't in db)
	public static User getUserProfileByID(int id) {
		Statement st = connect();
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT uname, pword, fname,lname,email,phoneNum,rating,image,userID FROM UserTable"
					+ " WHERE " + "userID="+id+";");
			if (rs.next()) {
				String unameDB = rs.getString("uname");
				String fnameDB = rs.getString("fname");
				String lnameDB = rs.getString("lname");
				String emailDB = rs.getString("email");
				String phoneNumDB = rs.getString("phoneNum");
				String imageDB = rs.getString("image");
				int userID = rs.getInt("userID");
				
				User u = new User(fnameDB, lnameDB, emailDB, phoneNumDB, unameDB, userID, imageDB);
				return u;
			}
		} catch (SQLException e) {
			System.out.println("Search failure: " + e.getMessage());
		}	
		return null;
	}
	
	//pass in an itemID and get back an Item object
	public static Item getItemByID(int id) {
		Statement st = connect();
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT * FROM ItemsTable WHERE itemID="+id+";");
			if (rs.next()) {
				String name = rs.getString("title");
				int quantity = rs.getInt("quantity");
				int sellerId = rs.getInt("sellingUser");
				int itemId = rs.getInt("itemID");
				float price = rs.getFloat("price");
				Category cat = Category.valueOf(rs.getString("category"));
				String img = rs.getString("image");
				String desc = rs.getString("description");
				Item i = new Item(name,price,cat,quantity,itemId,sellerId, img);
				if (!desc.equals("null"))
					i.setDescription(desc);
				else
					i.setDescription("");
				return i;
			}
		} catch (SQLException e) {
			System.out.println("Search failure: " + e.getMessage());
		}
		return null;
	}
	
	
	public static boolean sendWishListMessage(int itemID) {
		Statement st = connect();
		
		// Check for duplicate in the Wishlist
		String query = "SELECT * FROM WishlistTable WHERE wishingUser="+currUser.getUserID()
					+" AND itemID="+itemID+";";
		try {
			if(st.executeQuery(query).next())
				// Returns false if item already in the user's wishlist
				return false;			
		} catch (SQLException e) {
			System.out.println("Wishlist failure: "+e.getMessage());
		}
		
		//add to the WishlistTable
		query = "INSERT INTO WishlistTable(wishingUser, itemID)\n";
		query += "VALUES ("+currUser.getUserID() + ","+itemID+");";
		try {
			st.executeUpdate(query);
			
			//get time and date
			String time, date;
			String timePattern = "hh:mm:ss:a";
			String datePattern = "MM:dd:yyyy";
			SimpleDateFormat sdfTime = new SimpleDateFormat(timePattern);
			SimpleDateFormat sdfDate = new SimpleDateFormat(datePattern);
			time = sdfTime.format(new Date());
			date = sdfDate.format(new Date());
			
			//add to WishlistMessage
			query = "INSERT INTO WishlistMessage(wishingUser, itemID, isRead, sentTime, sentDate, sellingUser)\n";
			query += "VALUES ("+currUser.getUserID() + ","+itemID+ "," +false+", \'"+time+"\'"+", \'"+date+"\',"+getItemByID(itemID).getSellerID()+")";
			st.execute(query);
			
			return true;
		} catch (SQLException e) {
			System.out.println("Search failure: " + e.getMessage());
		}	
		return false;
	}
	
	public static boolean sendRatingMessage(int messageID) {
		Statement st = connect();
		
		// Check get buying and selling user id from wishlist message
		String query = "SELECT wishingUser, itemID, sellingUser FROM WishlistMessage WHERE wishlistID=" +messageID+";";
		ResultSet rs;
		try {
			rs = st.executeQuery(query);
			if(rs.next()) {
				int buyingUserID = rs.getInt("wishingUser");
				int itemID = rs.getInt("itemID");
				int sellingUserID = rs.getInt("sellingUser");
				
				if (!StoreDatabase.markAsSold(itemID)) {
					return false;
				}
				
				//"send" rating message to each user by adding to RatingMessage
				//get time and date
				String time, date;
				String timePattern = "hh:mm:ss:a";
				String datePattern = "MM:dd:yyyy";
				SimpleDateFormat sdfTime = new SimpleDateFormat(timePattern);
				SimpleDateFormat sdfDate = new SimpleDateFormat(datePattern);
				time = sdfTime.format(new Date());
				date = sdfDate.format(new Date());
				
				
				query = "INSERT INTO RatingMessage(ratedUser, ratingUser, itemID, isRead, sentTime, sentDate)\n";
				query += "VALUES("+buyingUserID+","+sellingUserID+","+itemID+","+false+",\'"+time+"\',\'"+date+"\'),";
				query += "("+sellingUserID+","+buyingUserID+","+itemID+","+false+",\'"+time+"\',\'"+date+"\');";
				
				System.out.println(query);
				
				st.execute(query);
			
				return true;
			}
				
				
				
		} catch (SQLException e) {
			System.out.println("Wishlist failure: "+e.getMessage());
		}
		
		return false;
	}
	
	public static boolean deleteUser(int userID) {
		Statement st = connect();
		String query = "DELETE FROM UserTable WHERE userID="+userID;
		try {
			st.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean deleteItem(int itemID) {
		Statement st = connect();
		String query = "DELETE FROM ItemsTable WHERE itemID="+itemID;
		try {
			st.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean deleteWishlistMessage(int messageID) {
		Statement st = connect();
		String query = "DELETE FROM WishlistMessage WHERE messageID="+messageID;
		try {
			st.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void loadWishlist(int userID) {
		//clear current wishlist vector
		StoreDatabase.currUser.removeWishlist();
		
		Statement st = connect();
		ResultSet rs;
		//check the WishlistTable
		String query = "SELECT itemID FROM WishlistTable WHERE wishingUser="+userID;
		try {
			rs = st.executeQuery(query);
			
			//add the item id to this vector
			Vector<Integer> itemIDs = new Vector<Integer>();
			while (rs.next()) {
				int itemID = rs.getInt("itemID");
				itemIDs.add(itemID);
			}	
			
			//get the items and add to user wishlist
			for (int i = 0; i < itemIDs.size(); i++) {
				Item item = StoreDatabase.getItemByID(itemIDs.get(i));
				StoreDatabase.currUser.addToWishlist(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadMessages(int userID) {
		//reset the messages
		StoreDatabase.currUser.removeAllMessages();
		
		Statement st = connect();
		ResultSet rs;
		//check the WishlistMessage
		
		String query = "SELECT * FROM WishlistMessage WHERE sellingUser="+userID+";";
		try {
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				int itemID = rs.getInt("itemID");
				String time = rs.getString("sentTime");
				String date = rs.getString("sentDate");
				int wishlistID = rs.getInt("wishlistID");
				Item item = StoreDatabase.getItemByID(itemID);
				int buyerId = rs.getInt("wishingUser");
				int sellerId = rs.getInt("sellingUser");
				StoreDatabase.currUser.addMessage(new WishlistMessage(item, time, date, wishlistID, sellerId, buyerId));
			}			
			
			query = "SELECT * FROM RatingMessage WHERE ratingUser="+userID+";";
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				int ratedUser = rs.getInt("ratedUser");
				int ratingUser = rs.getInt("ratingUser");
				int itemID = rs.getInt("itemID");
				String time = rs.getString("sentTime");
				String date = rs.getString("sentDate");
				int messageID = rs.getInt("ratingID");
				Item item = StoreDatabase.getItemByID(itemID);
				StoreDatabase.currUser.addMessage(new RatingMessage(item, time, date, messageID, ratingUser, ratedUser));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Message getMessageByID(int messageID, String type) {
		Statement st = connect();
		String query = "SELECT * FROM "+type+"Message";
		ResultSet rs;
		try {
			rs = st.executeQuery(query);
			if (rs.next()) {
				if (type.equals("Rating")) {
					int itemID = rs.getInt("itemID");
					int ratedUserID = rs.getInt("ratedUser");
					String time = rs.getString("sentTime");
					String date = rs.getString("sentDate");
					int ratingID = rs.getInt("ratingID");
					Item item = StoreDatabase.getItemByID(itemID);
					return new RatingMessage(item, time, date, ratingID, StoreDatabase.currUser.getUserID(), ratedUserID);
				}
				else if (type.equals("Wishlist")) {
					int itemID = rs.getInt("itemID");
					String time = rs.getString("sentTime");
					String date = rs.getString("sentDate");
					int wishlistID = rs.getInt("wishlistID");
					Item item = StoreDatabase.getItemByID(itemID);
					int buyerId = rs.getInt("wishingUser");
					int sellerId = rs.getInt("sellingUser");
					return new WishlistMessage(item, time, date, wishlistID, sellerId, buyerId);
				}
				else {
					System.out.println("FUCKKKK");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void addRating(int messageID, int rating) {
		Statement st = connect();
		//get the two users from the messageID
		String query = "SELECT ratedUser, ratingUser FROM RatingMessage WHERE ratingID="+messageID;
		ResultSet rs;
		try {
			rs = st.executeQuery(query);
			
			//find out which one isn't the current user
			if (rs.next()) {
				int user1 = rs.getInt("ratingUser");
				int user2 = rs.getInt("ratedUser");
				
				int userID = 0;
				if (user1 == currUser.getUserID()) {
					userID = user2;
				}
				else if (user2 == currUser.getUserID()) {
					userID = user1;
				}
				
				//add rating to RatingTable
				query = "INSERT INTO UserRatings(userID, rating)\n";
				query += "VALUES("+userID+", "+rating+")";
				
				st.execute(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//the only getter the rest of the program should need
	public static User getCurrUser() {
		return currUser;
	}
	
	public static void logout() {
		currUser = null;
	}

	//please leave private
	private static void setCurrUser(User currUser) {
		StoreDatabase.currUser = currUser;
	}
	
	public static boolean markAsSold(int itemID)
	{
		Item curr = getItemByID(itemID);
		if (curr.getQuantity() < 1)
			return false;
		curr.setQuantity(curr.getQuantity()-1);

		// Update the db with remaining quantity
		Statement st = connect();
		try {
			st.executeUpdate("UPDATE ItemsTable SET quantity="+curr.getQuantity()
					+ " WHERE itemID="+itemID+";");
			return true;
		} catch (SQLException e) {
			System.out.println("markAsSold: "+e.getMessage());
			return false;
		}
	}
	

}
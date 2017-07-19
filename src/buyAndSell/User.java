package buyAndSell;

import java.util.Vector;

public class User extends Guest {
	
	private String fName;
	private String lName;
	private String username;
	private String password; //storing this here for now- make sure to delete
	private String email;
	private float rating;
	private String image;
	private String phoneNumber;
	private Vector<Item> wishlist;
	private Vector<Message> messages;
	
	public User(String fName, String lName, String email, String phoneNum, String username, String password) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.phoneNumber = phoneNum;
		this.username = username;
		this.password = password;
		
		this.wishlist = new Vector<Item>();
		this.messages = new Vector<Message>();
	}
	
	
	
	//to be called when an item is sold
	public void itemSold(Item item) {
		item.setQuantity(item.getQuantity()-1);
		
		//send messages to buyer and seller to review each other
	}
	
	

	/*
	 * GETTERS AND SETTERS
	 */

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Vector<Item> getWishlist() {
		return wishlist;
	}

	public void addToWishlist(Item item) {
		this.wishlist.add(item);
	}

	public Vector<Message> getMessages() {
		return messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

package buyAndSell;


public class Item {
	
	private String name;
	private String image;
	private Category category;
	private int quantity;
	private double price;
	private int sellerID; // map/database ID that we assign to every user 
	private String description;
	private int uniqueID;
	
	public Item(String name, double d, Category category, int quantity, User sellingUser) {
		this.name = name;
		this.price = d;
		this.category = category;
		this.quantity = quantity;
		this.sellerID = sellingUser.getUniqueId();
	}
		
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	
	
	

}

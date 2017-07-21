package buyAndSell;


public class Item {
	
	private String name;
	private String image;
	private Category category;
	private int quantity;
	private double price;
	private String sellerID; // map/database ID that we assign to every user 
	private String description;
	private String uniqueID;
	
	public Item(String name, double d, Category category, int quantity, User sellingUser) {
		this.name = name;
		this.price = d;
		this.category = category;
		this.quantity = quantity;
		this.sellerID = sellingUser.getUniqueID();
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

	public String getSellerID() {
		return sellerID;
	}

	public void setSellerID(String sellerID) {
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

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	
	
	

}

package buyAndSell;


public class Item {
	
	private String name;
	private String image;
	private Category category;
	private int quantity;
	private float price;
	private int sellerID; // map/database ID that we assign to every user 
	private String description;
	private int itemID;

	
	public Item(String name, float price, Category category, int quantity, int itemID, int sellerID) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
		this.itemID = itemID;
		this.sellerID = sellerID;
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

	public float getPrice() {
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


	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

}
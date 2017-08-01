package buyAndSell;

public class WishlistMessage extends Message {

	public WishlistMessage(Item item, String time, String date, int messageId, int sellerId, int buyerId) {
		super(item,time,date, messageId, sellerId, buyerId);
		this.item = item;
		this.title = "Someone is interested in your "+ item.getName();
		this.msg = StoreDatabase.getUserProfileByID(buyerId).getUsername() + " added your " + item.getName()
				+ " to their Wishlist!";
		
	}
	
}

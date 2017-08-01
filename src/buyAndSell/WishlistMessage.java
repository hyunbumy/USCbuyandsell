package buyAndSell;

public class WishlistMessage extends Message {

	public WishlistMessage(Item item, String time, String date) {
		super(item, time, date);
		this.item = item;
		this.title = "Someone is interested in your "+ item.getName();
		this.msg = StoreDatabase.getCurrUser().getfName() + " added your " + item.getName()
				+ " to their Wishlist!";
		
	}
	
}

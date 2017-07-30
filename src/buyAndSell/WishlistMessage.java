package buyAndSell;

public class WishlistMessage extends Message {

	public WishlistMessage(Item item) {
		super();
		this.item = item;
		this.msg = StoreDatabase.getCurrUser().getfName() + " added your " + item.getName()
				+ " to their Wishlist!";
	}
}

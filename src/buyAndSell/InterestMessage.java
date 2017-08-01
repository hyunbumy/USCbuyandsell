package buyAndSell;

public class InterestMessage extends Message {

	public InterestMessage(Item item) {
		super();
		this.item = item;
		this.title = "Someone is interested in your "+ item.getName();
		this.msg = StoreDatabase.getCurrUser().getfName() + " added your " + item.getName()
				+ " to their Wishlist!";
	}
	
}

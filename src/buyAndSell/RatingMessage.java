package buyAndSell;

public class RatingMessage extends Message {
	
	public RatingMessage(Item item, String time, String date, int messageId, int sellerId, int buyerId) {
		super(item,time,date, messageId, sellerId, buyerId);
		this.item = item;
		this.title = item.getName() + " has been sold, please rate user";
		this.msg = "Please rate the user for the quality of your transaction.";
		
	}

}

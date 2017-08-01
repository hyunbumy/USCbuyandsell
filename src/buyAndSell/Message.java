package buyAndSell;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

	String msg;
	String title;
	Item item;
	String date;
	String time;
	boolean isRead;
	int messageId;
	int sellerId;
	int buyerId;
	
	public Message(Item item, String time, String date, int messageId, int sellerId, int buyerId) {
		//get time and date
		this.isRead = false;
		this.time = time;
		this.date = date;
		this.messageId = messageId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
	}
	
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	
	
}

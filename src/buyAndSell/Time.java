package buyAndSell;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends Thread {

	private String time;
	private String pattern = "hh:mm:ss:a";
	
	//multithreading
	public void run() {
		//run for the entirety of the app updating every second
		while (true) {
			//sleep for a second then update time
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				this.time = simpleDateFormat.format(new Date());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String getTime() {
		return time;
	}


	public Time() {
		this.start();
	}
	

}

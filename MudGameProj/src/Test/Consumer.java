package Test;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
private BlockingQueue queue;
	
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				
				String msg = (String)queue.take();
				
				System.out.println("¸Þ½ÃÁö¸¦ ²¨³À´Ï´Ù. : " + msg + "[" + queue.size() + "]");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}	
}

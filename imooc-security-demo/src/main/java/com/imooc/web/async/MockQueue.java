package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {

	/**下单，当这个字段有值的时候，表示接收到下单的消息*/
	private String placeOrder;
	/**订单完成，当这个字段有值的时候，表示接收到订单完成的消息*/
	/**订单完成，当这个字段有值的时候，表示接收到订单完成的消息*/
	private String completeOrder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) throws Exception {
//		System.out.println("接到下单请求"+placeOrder);
//		Thread.sleep(1000);
//		this.completeOrder = placeOrder;
//		System.out.println("下单请求处理完毕"+placeOrder);

		/**
		 * 因为是一个单独的线程，所以要新开一个线程，功能和上面一样
		 */
		new Thread(() -> {
			logger.info("接到下单请求, " + placeOrder);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			logger.info("下单请求处理完毕," + placeOrder);
		}).start();
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
	
	
}

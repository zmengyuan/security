package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 线程2代表的代码
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MockQueue mockQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 这个方法是spring初始化后要做的事，
	 * 也是用一个线程，如果不单独开一个线程，会阻塞主线程
	 * @param event
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() -> {
			while (true) {

				if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					
					String orderNumber = mockQueue.getCompleteOrder();
					logger.info("返回订单处理结果:"+orderNumber);
					//setResult表示异步完成了，返回信息
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
					mockQueue.setCompleteOrder(null);
					
				}else{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}
}
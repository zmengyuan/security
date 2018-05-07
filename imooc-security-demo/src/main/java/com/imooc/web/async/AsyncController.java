package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	


	/*
	同步情况
	 */
	@RequestMapping("/order")
	public String order() throws Exception {
		logger.info("主线程开始");
		Thread.sleep(1000);
		logger.info("主线程返回");
		return "success";
	}

	/*
	副线程自己运行
	主线程可以做其他事，tomcat的吞吐量可以较大提升
	 */
	@RequestMapping("/orders")
	public Callable<String> orders() throws Exception {
		logger.info("主线程开始");
//		实际是spring的一个线程
		Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info("副线程开始");
				Thread.sleep(1000);
				logger.info("副线程返回");
				return "success";
			}
		};
		logger.info("主线程返回");
		return  result;
	}



	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private  DeferredResultHolder deferredResultHolder;
	@RequestMapping("/deforders")
	public DeferredResult<String> deforders() throws Exception {
		logger.info("主线程开始");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);

		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);

		logger.info("主线程返回");
		return result;
	}



}

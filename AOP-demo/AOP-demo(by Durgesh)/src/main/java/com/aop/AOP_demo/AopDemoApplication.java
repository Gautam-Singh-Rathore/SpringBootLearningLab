package com.aop.AOP_demo;

import com.aop.AOP_demo.services.PaymentService;
import com.aop.AOP_demo.services.PaymentServiceImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AopDemoApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(AopDemoApplication.class, args);

		PaymentService paymentService = ctx.getBean(PaymentServiceImplementation.class);


		// Authentication , print : Payment Started
		paymentService.makePayment();


	}

}

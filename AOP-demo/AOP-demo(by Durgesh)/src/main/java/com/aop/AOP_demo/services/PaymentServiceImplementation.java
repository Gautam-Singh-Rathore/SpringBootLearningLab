package com.aop.AOP_demo.services;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImplementation implements PaymentService{

    @Override
    public void makePayment() {
        // Payment code
        System.out.println("Amount Debited !");
        System.out.println("Logic is running ....");
        System.out.println("Amount Credited !");
    }
}

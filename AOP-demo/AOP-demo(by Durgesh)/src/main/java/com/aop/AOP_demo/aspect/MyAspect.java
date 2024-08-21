package com.aop.AOP_demo.aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.aop.AOP_demo.services.PaymentServiceImplementation.makePayment())")
    public void printBefore(){
        System.out.println("Payment Started ...");
    }

    @After("execution(* com.aop.AOP_demo.services.PaymentServiceImplementation.makePayment())")
    public void printAfter(){
        System.out.println("Payment Completed ...");
    }
}

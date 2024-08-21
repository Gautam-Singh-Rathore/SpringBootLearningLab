package com.aop.AOP_demo.logging;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

    @Before("execution(public void com.aop.AOP_demo.services.UserService.logIn())")
    public void loggingAdvice1(){
        System.out.println("Before advice for logIn is executed");
    }

    @After("execution(public void com.aop.AOP_demo.services.UserService.logIn())")
    public void loggingAdvice2(){
        System.out.println("Running after advice for logIn");
    }

    @Around("execution(public void com.aop.AOP_demo.services.UserService.logIn())")
    public void loggingAdvice3(){
        System.out.println("Before and After invoking method logIn()");
    }

    @AfterThrowing("execution(public void com.aop.AOP_demo.services.UserService.logOut())")
    public void loggingAdvice4() {
        System.out.println("Exception thrown in logOut method");
    }

    @AfterReturning("execution(public void com.aop.AOP_demo.services.UserService.logOut())")
    public void loggingAdvice5() {
        System.out.println("AfterReturning advice for logOut is run");
    }

    @Pointcut("execution(public * com.aop.AOP_demo.services.UserService.*().. )")
    public void pointCut() {
    }
    @Pointcut("execution(public * com.aop.AOP_demo.services.UserService.*().. )")
    public void pointCut1() {
    }

    @Before("pointCut() || pointCut1()")
    public void loggingAdvice6() {
        System.out.println("Before advice using pointcut is executed");
    }


}

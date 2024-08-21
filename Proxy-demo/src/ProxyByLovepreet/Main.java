package ProxyByLovepreet;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        // Use cases of proxy -
        // 1. @Cacheable and @Transaction
        // 2. Aspect Oriented Programming
        // Implementation of proxies -
        //  i. Dynamic Proxy (comes inbuilt in JDK) - implement only on interface level
        //  ii. CGLIB
        // Uses Reflection - at runtime (it is an API just like stream)

        Man mohan = new Man("Mohan" , 30 , "jaipur" , "india") ;
        ClassLoader mohanClassLoader = mohan.getClass().getClassLoader();
        Class[] interfaces = mohan.getClass().getInterfaces();
        Person proxyMohan = (Person) Proxy.newProxyInstance(mohanClassLoader , interfaces ,  new PersonInvocationHandler(mohan));

        proxyMohan.introduce("Gautam");
    }
}

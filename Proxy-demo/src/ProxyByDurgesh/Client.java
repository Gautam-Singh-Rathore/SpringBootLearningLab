package ProxyByDurgesh;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {

        Map fees =(Map) Proxy.newProxyInstance(
                HashMap.class.getClassLoader() ,
                new Class[]{Map.class},
                new ObjectHandler(new HashMap<>())

        );

        fees.put("java" ,  2000);
        System.out.println(fees);
    }
}

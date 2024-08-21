package ProxyByDurgesh;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ObjectHandler implements InvocationHandler {
    Object object;

    public ObjectHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Logic before method call
        if(method.getName().contains("put")){
            if(args[0].equals("java")){
                System.out.println("Invalid key : java");
                return null;
            }
        }
        System.out.println("Logic Before Method Call ...");

        Object invoke = method.invoke(object , args);

        // Logic after method call
        System.out.println("logic After Method Call  !!!");

        return invoke;
    }
}

//package ProxyByLovepreet;
//
//import java.lang.reflect.Method;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//public class CgClass {
//    static class Original {
//        public void originalMethod(String s) {
//            System.out.println(s);
//        }
//    }
//
//    static class Handler implements MethodInterceptor {
//        private final Original original;
//
//        public Handler(Original original) {
//            this.original = original;
//        }
//
//        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//            System.out.println("BEFORE");
//            method.invoke(original, args);
//            System.out.println("AFTER");
//            return null;
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            Original original = new Original();
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(Original.class);
//            enhancer.setCallback(new Handler(original));
//
//            Original proxy = (Original) enhancer.create();
//            proxy.originalMethod("Hello");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

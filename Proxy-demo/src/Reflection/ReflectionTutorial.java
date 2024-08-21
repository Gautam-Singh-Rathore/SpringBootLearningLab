package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTutorial {
    public static void main(String[] args) throws Exception {
        Cat myCat = new Cat("Stella" , 6);
        Field[] catFields = myCat.getClass().getDeclaredFields();

        // Setting private fields
        for(Field field : catFields){
            if(field.getName().equals("name")){
                field.setAccessible(true);
                field.set(myCat , "Jimmy McGill");
            }

        }
        System.out.println(myCat.getName());

        // Getting Method
        Method[] catMathods = myCat.getClass().getDeclaredMethods();
        for(Method method : catMathods){
            // Invoking private method
           if(method.getName().equals("heyThisIsPrivate")){
               method.setAccessible(true);
               method.invoke(myCat);
           }

           // Invoking static methods
            if(method.getName().equals("thisIsPublicStaticMethod")){
                method.setAccessible(true);
                method.invoke(null);
            }
        }
    }
}

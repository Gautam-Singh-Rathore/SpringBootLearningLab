package com.aop.AOP_demo.services;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Getter
    @Setter
    @AllArgsConstructor
    public class User{
        private String name;
        private int age;
        private String address;
    }

    private User user;

    public UserService() {
        user = new User("Gautam Singh" , 19 , "Jaipur , India");
    }

    public void logIn(){
        System.out.println("Logging user in ");
    }

    public void logOut() throws Exception{
        System.out.println("Logging user out ");
        throw new Exception("Unable to logout the user");
    }
}

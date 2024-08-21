package com.aop.AOP_demo.RestAPI;
import com.aop.AOP_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    @Autowired
    private UserService userService ;

    @GetMapping("/")
    public String userLogIn(){
        //Call the method in MainService , which in turn calls the UserService
        userService.logIn();
        return "User login endpoint called successfully";
    }
}

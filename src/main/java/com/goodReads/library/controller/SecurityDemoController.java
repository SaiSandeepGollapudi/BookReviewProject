package com.goodReads.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityDemoController {

    @GetMapping("/greet/{username}")
        public String greet(@PathVariable String username){
            return "Hello "+username;
        }

    //Url -> localhost:8080/admin/greet/john
    //RequestMapperHandlerMapping Map<String(url),String(controller method)>
    //key -> admin/greet/{username} -> Controller Method -> SecurityDemoController.adminGreet
    @GetMapping("admin/greet/{username}")
    public String adminGreet(@PathVariable String username){
        return "Hello Admin "+username;
    }

    @GetMapping("/csrf")
    public String homePage(HttpServletRequest request){// it will extract the request and take the csrf token from it
CsrfToken csrfToken= (CsrfToken) request.getAttribute(CsrfToken.class.getName());
return csrfToken.getToken();// it will return token to user which is our postman
    }

    @GetMapping("/readoauth")
        public String readOauth(Authentication authentication){//to retrieve the values of user who is currently logged in, we have access to this data till he is logged in
        //It will extract the authentication information of the current user and return it as a JSON string.
        ObjectMapper mapper= new ObjectMapper();//It initializes an ObjectMapper instance, which is a part of Jackson library used for converting Java objects to JSON & vice versa.
        try{
            return mapper.writeValueAsString(authentication);// Convert the Authentication object to a JSON string
        }
        catch(JsonProcessingException e)
        // Handle JSON processing exception
        {
            throw new RuntimeException(e);
    }

}

}

package com.auction;

import auction.domain.User;
import auction.service.RegistrationMgr;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class RegistrationController {

    public RegistrationMgr registrationMgr;


    @ResponseBody
    @RequestMapping(path = "registerUser/{email}", method = GET, produces = "application/json")
    public String registerUser(@PathVariable String email) {

        registrationMgr = new RegistrationMgr();

        if (email.equals("")) {
            return "Not a valid email";
        }

        if (registrationMgr.getUser(email) != null) {
            return "Already an user with that email!";
        }

        User u = registrationMgr.registerUser(email);

        if(u == null){
            return "Registration error! Check if your email has an @ symbol!";
        }

        if (u.getEmail().equals(email)) {

            return "registration succesfull!";
        }
        return "Registration error!";

    }

    @ResponseBody
    @RequestMapping(path = "getUser/{email}", method = GET, produces = "application/json")
    public String getUser(@PathVariable String email) {

        registrationMgr = new RegistrationMgr();


        User u  = registrationMgr.getUser(email);

        String jsonObject = new Gson().toJson(u);

        return jsonObject;

    }
}
package org.launchcode.ticketsystem.controllers;




import org.launchcode.ticketsystem.models.data.UserDao;
import org.launchcode.ticketsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Freddie Beasley
 */


@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "user-login", method = RequestMethod.GET)
    public String displayUserLogin(Model model) {

        model.addAttribute("title", "Ticket System");
        model.addAttribute("welcome", "Please log in");
        model.addAttribute(new User());

        return "login/user-login";
    }

    @RequestMapping(value = "user-login", method = RequestMethod.POST)
    public String processUserLogin(@ModelAttribute @Valid User newUser, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Ticket System");
            return "login/user-login";


        }
        if (
                newUser.getUserName() != null && newUser.getPassWord() != null) {
            if (newUser.getUserName().equals("Freddie") && newUser.getPassWord().equals("Launchcode!")) {
                model.addAttribute("title", "Ticket System");
                model.addAttribute("userName", newUser.getUserName());
                return "login/tech-home";
            } else {
                model.addAttribute("welcome", "Please log in");
                model.addAttribute("msg", "Invalid Details");
                return "login/user-login";
            }
        } else {
            model.addAttribute("welcome", "Please log in");
            model.addAttribute("msg", "Please enter details");
            return "login/user-login";
        }
    }
}



















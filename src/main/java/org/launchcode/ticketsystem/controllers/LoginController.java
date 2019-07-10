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

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Ticket System");

        return "login/index";
    }
    @RequestMapping (value = "user-login", method = RequestMethod.GET)
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
        model.addAttribute("title", "Ticket System");
        model.addAttribute("userName", newUser.getUserName());

        return "login/tech-home";
        }
    }












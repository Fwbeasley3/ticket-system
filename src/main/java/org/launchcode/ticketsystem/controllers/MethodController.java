package org.launchcode.ticketsystem.controllers;


import org.launchcode.ticketsystem.models.Ticket;
import org.launchcode.ticketsystem.models.Method;
import org.launchcode.ticketsystem.models.data.MethodDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("method")
public class MethodController {

    @Autowired  // Spring will do the work of creating a class that implements MethodDao
    private MethodDao methodDao;  //creates a private field categoryDao of type MethodDao. This object will be the mechanism with which we interact with objects stored in the database

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("methods", methodDao.findAll());
        model.addAttribute("title", "Methods");

        return "method/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMethodForm(Model model) {
        model.addAttribute("title", "Add Method");
        model.addAttribute(new Method());

        return "method/add";
    }

    @RequestMapping(value = "add" , method = RequestMethod.POST)
    public String processAddMethodForm(Model model, @ModelAttribute @Valid Method method, Errors errors) {

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Method");
            return "method/add";
        }

        methodDao.save(method);
        return "redirect:";
    }

    @RequestMapping(value = "method", method = RequestMethod.GET)
    public String method(Model model, @RequestParam int id){

        Method method = methodDao.findOne(id);
        List<Ticket> tickets = method.getTickets();
        model.addAttribute("tickets", tickets);
        model.addAttribute("title", "Tickets in method: " + method.getName());
        return "ticket/index";

    }



}

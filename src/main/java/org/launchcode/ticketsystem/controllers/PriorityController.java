package org.launchcode.ticketsystem.controllers;


import org.launchcode.ticketsystem.models.Priority;
import org.launchcode.ticketsystem.models.Ticket;
import org.launchcode.ticketsystem.models.data.PriorityDao;
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
@RequestMapping("priority")
public class PriorityController {

    @Autowired  // Spring will do the work of creating a class that implements CategoryDao
    private PriorityDao priorityDao;  //creates a private field categoryDao of type CategoryDao. This object will be the mechanism with which we interact with objects stored in the database

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("priorities", priorityDao.findAll());
        model.addAttribute("title", "Priorities");

        return "priority/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPriorityForm(Model model) {
        model.addAttribute("title", "Add Priority");
        model.addAttribute(new Priority());

        return "priority/add";
    }

    @RequestMapping(value = "add" , method = RequestMethod.POST)
    public String processAddPriorityForm(Model model, @ModelAttribute @Valid Priority priority, Errors errors) {

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Priority");
            return "priority/add";
        }

        priorityDao.save(priority);
        return "redirect:";
    }

    @RequestMapping(value = "priority", method = RequestMethod.GET)
    public String priority(Model model, @RequestParam int id){

        Priority priority = priorityDao.findOne(id);
        List<Ticket> tickets = priority.getTickets();
        model.addAttribute("tickets", tickets);
        model.addAttribute("title", "Tickets in priority: " + priority.getName());
        return "ticket/index";

    }



}

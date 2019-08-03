package org.launchcode.ticketsystem.controllers;


import org.launchcode.ticketsystem.models.Ticket;
import org.launchcode.ticketsystem.models.data.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("tickets", ticketDao.findAll());
        model.addAttribute("title", "Tickets");

        return "ticket/index";


    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddTicketForm(Model model) {
        model.addAttribute("title", "Add Ticket");
        model.addAttribute(new Ticket());
        return "ticket/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTicketForm(@ModelAttribute @Valid Ticket newTicket, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ticket");
            return "ticket/add";

        }
        ticketDao.save(newTicket);
        return "redirect:";

    }

    @RequestMapping(value= "view/{id}")
    public String viewPost(@PathVariable("id")Integer id, Model model){
       Ticket ticket = ticketDao.findOne(id);
       model.addAttribute("ticket",ticket);





       return "ticket/view";
    }


}



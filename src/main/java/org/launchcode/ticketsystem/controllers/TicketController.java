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

    //@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    //public String displayEditForm(Model model, @PathVariable int id) {

      //  Ticket ticket = ticketDao.findOne(id);    //look up cheese object by id
       // model.addAttribute("title", ticket.getSubject());//pass ticket object to model
       // model.addAttribute("description", ticket.getDescription());
       // model.addAttribute("id", id);

        //return "ticket/edit";


    //}

    //@RequestMapping(value = "edit", method = RequestMethod.POST)
    //public String processEditForm(int id, String subject, String description ,Model model) {
      //  Ticket ticket = ticketDao.findOne(id);     //look up cheese object by id

        //ticketDao.save(ticket);
        //return "redirect:";

    //}
}

package org.launchcode.ticketsystem.controllers;


import org.launchcode.ticketsystem.models.*;
import org.launchcode.ticketsystem.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private PriorityDao priorityDao;

    @Autowired
    private StatusDao statusDao;

    @Autowired
    private MethodDao methodDao;

    @Autowired
    private CategoryDao categoryDao;

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
        model.addAttribute("priorities", priorityDao.findAll());
        model.addAttribute("statuses", statusDao.findAll());
        model.addAttribute("methods", methodDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        return "ticket/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTicketForm(@ModelAttribute @Valid Ticket newTicket, Errors errors, @RequestParam int priorityId,
                                       @RequestParam int statusId, @RequestParam int methodId, @RequestParam int categoryId, Model model) {

       if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ticket");
            model.addAttribute("priorities", priorityDao.findAll());
            model.addAttribute("statuses", statusDao.findAll());
            model.addAttribute("methods", methodDao.findAll());
            model.addAttribute("categories", categoryDao.findAll());
            return "ticket/add";



        }
        Priority priority= priorityDao.findOne(priorityId);
        Status status = statusDao.findOne(statusId);
        Method method = methodDao.findOne(methodId);
        Category category = categoryDao.findOne(categoryId);

        newTicket.setPriority(priority);
        newTicket.setStatus(status);
        newTicket.setMethod(method);
        newTicket.setCategory(category);
        ticketDao.save(newTicket);
        return "ticket/view";

    }

    @RequestMapping(value= "edit/{ticketId}", method = RequestMethod.GET)
    public String editPost(@PathVariable int ticketId, Model model){
        model.addAttribute("title", "Edit Ticket");
        model.addAttribute("ticket", ticketDao.findOne(ticketId));
        model.addAttribute("priorities", priorityDao.findAll());
        model.addAttribute("statuses", statusDao.findAll());
        model.addAttribute("methods", methodDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());

        return "ticket/edit";



    }

    @RequestMapping(value = "edit/{ticketId}", method = RequestMethod.POST)
    public String editPost(@PathVariable ("ticketId")  Integer ticketId,Model model,@ModelAttribute @Valid Ticket newTicket,@RequestParam int priorityId,
                           @RequestParam int statusId,@RequestParam int methodId,@RequestParam int categoryId, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Ticket");
            //model.addAttribute("priorities", priorityDao.findAll());
            return "ticket/edit";
        }

        Ticket ticket = ticketDao.findOne(ticketId);
        ticket.setRequester(ticket.getRequester());
        ticket.setLocation(ticket.getLocation());
        ticket.setSubject(ticket.getSubject());
        ticket.setDescription(ticket.getDescription());
        ticket.setResolution(ticket.getResolution());
        ticket.setPriority(priorityDao.findOne(priorityId));
        ticket.setStatus(statusDao.findOne(statusId));
        ticket.setMethod(methodDao.findOne(methodId));
        ticket.setCategory(categoryDao.findOne(categoryId));
        Priority priority = priorityDao.findOne(priorityId);
        priority.setName(priority.getName());
        Status status = statusDao.findOne(statusId);
        status.setName(status.getName());
        Method method = methodDao.findOne(methodId);
        method.setName(method.getName());
        Category category = categoryDao.findOne(categoryId);
        category.setName(category.getName());
        newTicket.setPriority(priority);
        newTicket.setStatus(status);
        newTicket.setMethod(method);
        newTicket.setCategory(category);;
        ticketDao.save(newTicket);




        return "ticket/view";

    }

    @RequestMapping(value= "view/{id}")
    public String viewPost(@PathVariable("id")Integer id, Model model){
       Ticket ticket = ticketDao.findOne(id);
       model.addAttribute("ticket",ticket);
       return "ticket/view";
    }
}






















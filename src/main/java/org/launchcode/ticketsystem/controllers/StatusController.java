package org.launchcode.ticketsystem.controllers;

import org.launchcode.ticketsystem.models.Priority;
import org.launchcode.ticketsystem.models.Status;
import org.launchcode.ticketsystem.models.Ticket;
import org.launchcode.ticketsystem.models.data.StatusDao;
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
@RequestMapping("status")
public class StatusController {

    @Autowired  // Spring will do the work of creating a class that implements StatusDao
    private StatusDao statusDao;  //creates a private field categoryDao of type StatusDao. This object will be the mechanism with which we interact with objects stored in the database

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("statuses", statusDao.findAll());
        model.addAttribute("title", "Statuses");

        return "status/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddStatusForm(Model model) {
        model.addAttribute("title", "Add Status");
        model.addAttribute(new Status());

        return "status/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddStatusForm(Model model, @ModelAttribute @Valid Status status, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Status");
            return "status/add";
        }

        statusDao.save(status);
        return "redirect:";
    }

    @RequestMapping(value = "status", method = RequestMethod.GET)
    public String status(Model model, @RequestParam int id) {

        Status status = statusDao.findOne(id);
        List<Ticket> tickets = status.getTickets();
        model.addAttribute("tickets", tickets);
        model.addAttribute("title", "Tickets in status: " + status.getName());
        return "ticket/index";

    }
}
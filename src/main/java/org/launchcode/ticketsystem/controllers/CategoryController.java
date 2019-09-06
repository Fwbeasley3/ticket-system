package org.launchcode.ticketsystem.controllers;

import org.launchcode.ticketsystem.models.Category;
import org.launchcode.ticketsystem.models.Ticket;
import org.launchcode.ticketsystem.models.data.CategoryDao;
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
@RequestMapping("category")
public class CategoryController {

    @Autowired  // Spring will do the work of creating a class that implements StatusDao
    private CategoryDao categoryDao;  //creates a private field categoryDao of type StatusDao. This object will be the mechanism with which we interact with objects stored in the database

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());

        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCategoryForm(Model model, @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {

        Category category = categoryDao.findOne(id);
        List<Ticket> tickets = category.getTickets();
        model.addAttribute("tickets", tickets);
        model.addAttribute("title", "Tickets in status: " + category.getName());
        return "ticket/index";

    }
}


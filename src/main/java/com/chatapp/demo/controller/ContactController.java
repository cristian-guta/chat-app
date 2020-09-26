package com.chatapp.demo.controller;

import com.chatapp.demo.model.Contacts;
import com.chatapp.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    public ContactService contactService;

    @GetMapping("/addContact")
    public String addContactshowForm(@ModelAttribute("contact") Contacts contact) {

        return "addContact";
    }

    @PostMapping("/addContact")
    public String addContactProcessForm(@ModelAttribute("contact") Contacts contact, Model model) {
        contactService.addContact(contact);
        model.addAttribute("contact", contact);
        return "redirect:/home";
    }
}

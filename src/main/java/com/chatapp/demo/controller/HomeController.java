package com.chatapp.demo.controller;

import com.chatapp.demo.model.Contacts;
import com.chatapp.demo.model.User;
import com.chatapp.demo.repository.ContactRepository;
import com.chatapp.demo.repository.UserRepository;
import com.chatapp.demo.service.ContactService;
import com.chatapp.demo.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public HomeService homeService;

    @Autowired
    public ContactRepository contactRepository;

    @Autowired
    public ContactService contactService;

    @GetMapping
    public String homeView(Model model) {
        User user = homeService.getCurrentUser();
        model.addAttribute("contacts", user.getContacts());
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping("/search")
    public String searchForm(@RequestParam("searchParam") String searchParam,
                             Model model){
        User user = homeService.getCurrentUser();
        List<Contacts> contactsFound = contactRepository.findContactsByFirstNameOrLastName(searchParam);
        if(contactsFound.isEmpty()){
            contactsFound = user.getContacts();
        }
        model.addAttribute("contacts", contactsFound);
        return "home";
    }

}

package com.chatapp.demo.controller;

import com.chatapp.demo.model.User;
import com.chatapp.demo.repository.UserRepository;
import com.chatapp.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String editUserInfo(@ModelAttribute("user") User user, Model model) {
        user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "editUserInfo";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user){
        User userr = userService.getCurrentUser();
        userr.setUsername(user.getUsername()).
                setFirstName(user.getFirstName()).
                setLastName(user.getLastName())
                .setPhoneNumber(user.getPhoneNumber());
        userRepository.save(userr);
        return "redirect:/home";
    }
}

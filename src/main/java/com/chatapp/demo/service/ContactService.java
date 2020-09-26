package com.chatapp.demo.service;

import com.chatapp.demo.model.Contacts;
import com.chatapp.demo.model.User;
import com.chatapp.demo.repository.ContactsRepository;
import com.chatapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    public UserRepository userRepository;
    public ContactsRepository contactsRepository;

    @Autowired
    public ContactService(UserRepository userRepository, ContactsRepository contactsRepository) {
        this.userRepository = userRepository;
        this.contactsRepository = contactsRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }

    public Contacts addContact(Contacts contact) {
        User user = getCurrentUser();
        List<Contacts> userContacts = user.getContacts();
        userContacts.add(contact);
        user.setContacts(userContacts);
        return contactsRepository.save(contact);
    }
}

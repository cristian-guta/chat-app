package com.chatapp.demo.service;

import com.chatapp.demo.model.Role;
import com.chatapp.demo.model.User;
import com.chatapp.demo.repository.RoleRepository;
import com.chatapp.demo.repository.UserRepository;
import com.chatapp.demo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private static UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else {
            throw new UsernameNotFoundException("Nu exista username-ul " + username);
        }
    }

    public void save(User newUser){
        Role role = roleRepository.findByRoleName("USER");
        User user = new User().setContacts(newUser.getContacts())
                .setEnabled(newUser.getEnabled())
                .setFirstName(newUser.getFirstName())
                .setLastName(newUser.getLastName())
                .setPhoneNumber(newUser.getPhoneNumber())
                .setRole(role)
                .setUsername(newUser.getUsername())
                .setPassword(bcryptEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }
}

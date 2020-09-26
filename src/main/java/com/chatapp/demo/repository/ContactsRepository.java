package com.chatapp.demo.repository;

import com.chatapp.demo.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
}

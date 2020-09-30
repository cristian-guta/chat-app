package com.chatapp.demo.repository;

import com.chatapp.demo.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Integer> {

    @Query(value = "select c from Contacts c where lower(c.firstName) like :searchParam or lower(c.lastName) like :searchParam")
    List<Contacts> findContactsByFirstNameOrLastName(String searchParam);

}

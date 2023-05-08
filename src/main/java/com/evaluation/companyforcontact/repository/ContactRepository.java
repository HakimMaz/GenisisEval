package com.evaluation.companyforcontact.repository;

import com.evaluation.companyforcontact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository  extends JpaRepository<Contact,Long> {
    List<Contact> findAllByTvaNumber(Long TvaNumber);
    Contact findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}

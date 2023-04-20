package com.evaluation.companyforcontact.repository;

import com.evaluation.companyforcontact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact,Long> {
}

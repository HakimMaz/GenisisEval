package com.evaluation.companyforcontact.service;


import com.evaluation.companyforcontact.dto.ContactUpdateDTO;
import com.evaluation.companyforcontact.model.Contact;

import java.util.List;
import java.util.UUID;


public interface ContactService {

    public Contact saveContact(Contact contactRequest) throws Exception;

    public List<Contact> allContact();

    public boolean contactExists(UUID uuid);

    public Contact getContact(UUID uuid);

    public Contact editContact(UUID uuid, ContactUpdateDTO updateRequest);

    public  void deleteContact(UUID uuid);
}


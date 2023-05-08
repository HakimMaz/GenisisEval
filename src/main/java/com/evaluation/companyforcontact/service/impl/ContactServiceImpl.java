package com.evaluation.companyforcontact.service.impl;

import com.evaluation.companyforcontact.dto.ContactUpdateDTO;
import com.evaluation.companyforcontact.exception.CompanyNotFoundException;
import com.evaluation.companyforcontact.exception.ContactNotFoundException;
import com.evaluation.companyforcontact.model.Contact;
import com.evaluation.companyforcontact.repository.ContactRepository;
import com.evaluation.companyforcontact.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@CommonsLog
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

   @Override
    public Contact saveContact(Contact contact) throws Exception {
        try {
            Contact savedContact = contactRepository.save(contact);
            return savedContact;
        } catch ( Exception e) {
            throw new Exception("Failed to create contact: " + e.getMessage());
                        }
    }
    @Override
    public Contact getContact(UUID uuid){
       return contactRepository.findByUuid(uuid);

    }
    @Override
    public List<Contact> allContact() {
        return contactRepository.findAll();
    }
    @Override
    public boolean contactExists(UUID uuid) {
        return contactRepository.findByUuid(uuid) != null;
   }
    @Override
    public void deleteContact(UUID uuid) {
        Contact optionalContact = contactRepository.findByUuid(uuid);
                if (optionalContact!=null) {
                    contactRepository.delete(optionalContact);
                } else {
                    throw new ContactNotFoundException("Contact not found with UUID: " + uuid);
                }
    }
    @Override
    public Contact editContact(UUID uuid, ContactUpdateDTO updateRequest){
       try{
           Contact updatedContact = contactRepository.findByUuid(uuid);
           if (updatedContact != null) {
               updatedContact.setFirstName(updateRequest.getFirstName());
               updatedContact.setLastName(updateRequest.getLastName());
               updatedContact.setContactType(updateRequest.getContactType());
                contactRepository.save(updatedContact);
           }
           return updatedContact;
       }catch(ContactNotFoundException e) {
           throw new ContactNotFoundException("Contact with UUID " + uuid + " not found");
       }

    }
}

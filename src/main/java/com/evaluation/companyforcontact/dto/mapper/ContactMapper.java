package com.evaluation.companyforcontact.dto.mapper;

import com.evaluation.companyforcontact.dto.ContactCreateDTO;
import com.evaluation.companyforcontact.model.Contact;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContactMapper {

    public Contact mapJsonToContact(ContactCreateDTO contactRequest){
        return Contact.builder()
                .uuid(UUID.randomUUID())
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .contactType(contactRequest.getContactType())
                .address(contactRequest.getAddress())

                .build();

    }

    //the first solution was based on adding tva while creating freelancer
    //but this method is not used because tva will be added when we add
    //freelancer to  company
    public Contact mapJsonToFreelancer(ContactCreateDTO contactRequest){


            return Contact.builder()
                    .uuid(UUID.randomUUID())
                    .firstName(contactRequest.getFirstName())
                    .lastName(contactRequest.getLastName())
                    .contactType(contactRequest.getContactType())
                    .tvaNumber(contactRequest.getTvaNumber())
                    .address(contactRequest.getAddress())

                    .build();

        }

}


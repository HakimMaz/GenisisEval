package com.evaluation.companyforcontact.Controller;

import com.evaluation.companyforcontact.dto.ContactCreateDTO;
import com.evaluation.companyforcontact.dto.ContactUpdateDTO;
import com.evaluation.companyforcontact.dto.mapper.ContactMapper;
import com.evaluation.companyforcontact.model.Contact;
import com.evaluation.companyforcontact.service.ContactService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController


@RequestMapping("/api/v1/contacts")
@Tag( name = "Contact management", description = "All possible operations on contact")
@Api(value = "api", description = "the api API")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactMapper mapper;

    @ApiOperation(value = "Create new contact", nickname = "createContact", notes = "Allow user to create contact", response = Contact.class, tags = {"contact"})
       @ApiResponses(value = {
               @ApiResponse(code = 200, message = "Contact Created Successfully !", response = Contact.class),
               @ApiResponse(code = 400, message = "Bad request!"),
               @ApiResponse(code = 500, message = "Internal server error!")
       })
    @PostMapping("/create")
    public ResponseEntity<Contact> createContact( @RequestBody ContactCreateDTO contactRequest)  {
            try {
                Contact contact = mapper.mapJsonToContact(contactRequest);
                var savedContact = contactService.saveContact(contact);
                return  new ResponseEntity<>(savedContact,HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

   @GetMapping("/all")
    public List<Contact>getAllContacts(){
       return contactService.allContact();
   }

    @ApiResponses(value = {
            @ApiResponse(code=200,message = "contact deleted !" ),
            @ApiResponse(code=500,message = "Internal server error!")
    })
   @DeleteMapping("/delete/{uuid}")
    public ResponseEntity delete(@PathVariable UUID uuid) {
        try {
            contactService.deleteContact(uuid);
            return ResponseEntity.ok().body(java.util.Map.of("message", "Contact deleted Successfully", "deletedUuid", uuid));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(java.util.Map.of("status", HttpStatus.NOT_FOUND, "message", "Contact not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(java.util.Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR, "message", e.getMessage()));
        }
    }

    @ApiResponses(value = {
                @ApiResponse(code=200,message = "contact deleted !" ),
                @ApiResponse(code=500,message = "Internal server error!")
        })
    @PutMapping("/update/{uuid}")
    public ResponseEntity update(@PathVariable UUID uuid, @RequestBody  ContactUpdateDTO requestUpdate){
        try {
                   Contact updatedContact=contactService.editContact(uuid,requestUpdate);
                     return new ResponseEntity<>(updatedContact,HttpStatus.ACCEPTED);
               } catch (EntityNotFoundException e) {
                   return ResponseEntity.status(HttpStatus.NOT_FOUND)
                           .body(java.util.Map.of("status", HttpStatus.NOT_FOUND, "message", "Contact not found"));
               } catch (Exception e) {
                   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body(java.util.Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR, "message", e.getMessage()));
               }

    }

}

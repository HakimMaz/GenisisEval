package com.evaluation.companyforcontact.service.impl;

import com.evaluation.companyforcontact.dto.CompanyUpdateDTO;
import com.evaluation.companyforcontact.exception.CompanyAlreadyExistsException;
import com.evaluation.companyforcontact.exception.CompanyNotFoundException;
import com.evaluation.companyforcontact.model.Company;
import com.evaluation.companyforcontact.model.CompanyContact;
import com.evaluation.companyforcontact.model.Contact;
import com.evaluation.companyforcontact.model.Type;
import com.evaluation.companyforcontact.repository.CompanyContactRepository;
import com.evaluation.companyforcontact.repository.CompanyRepository;
import com.evaluation.companyforcontact.repository.ContactRepository;
import com.evaluation.companyforcontact.service.CompanyService;
import com.evaluation.companyforcontact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CompanyContactRepository companyContactRepository;


    @Override
    public Company saveCompany(Company requestCompany) throws Exception {
        try {
                    Company company=companyRepository.findByTvaNumber(requestCompany.getTvaNumber());
                    if(company !=null){
                        throw new CompanyAlreadyExistsException("Company already exists");

                    }else{
                        Company savedCompany = companyRepository.save(requestCompany);
                        return savedCompany;
                    }

        } catch ( Exception e) {
                    throw new Exception("Failed to create company: " + e.getMessage());
                                }
    }
    @Override
    public List<Company> allcompanies(){
        return companyRepository.findAll();
    }
    @Override
    public Company CompanyWithTvaNumber(Long tvaNumber) {
        Company company = companyRepository.findByTvaNumber(tvaNumber);
                if (company == null) {
                    throw new CompanyNotFoundException("Company not found for TVA number " + tvaNumber);
                }
                return company;
    }
    @Override
    public boolean tvaValidation(Long tvaNumber){
        List<Company> companies=allcompanies();
        return  companies.stream()
                .anyMatch(company -> company.getTvaNumber().equals(tvaNumber));
    }
    @Override
    @Transactional
    public void addContactToCompany(UUID uuidContact, Long tvaNumber){
        Contact contact = contactService.getContact(uuidContact);
        Company company = companyRepository.findByTvaNumber(tvaNumber);
        CompanyContact companyContact =new CompanyContact();
        companyContact.setContact(contact);
        companyContact.setCompany(company);
        companyContactRepository.save(companyContact);

        if(contact.getContactType().equals(Type.FREELANCER))
            contact.setTvaNumber(tvaNumber);
            contactRepository.save(contact);
     }
   @Override
   public Company getCompanyByUuid(UUID uuid){
        return companyRepository.findByUuid(uuid);
   }
    @Override
    @Transactional
    public Company editCompany(UUID uuid, CompanyUpdateDTO requestUpdate) {

        Company company= companyRepository.findByUuid(uuid);
        Long oldTvaNumber=company.getTvaNumber();
        if(company==null)
            throw new CompanyNotFoundException("No company found for uuid : "+uuid);

        company.setAddress(requestUpdate.getAddress());
        if(requestUpdate.getTvaNumber()!=oldTvaNumber){
            //find all contact that belong to this company
             List<Contact>contacts=contactRepository.findAllByTvaNumber(oldTvaNumber);
            contacts.stream()
                    .forEach(contact ->{
                        contact.setTvaNumber(requestUpdate.getTvaNumber());
                        contactRepository.save(contact);
                            });
        }
        companyRepository.save(company);
        return company ;
    }

}

package com.evaluation.companyforcontact.service;

import com.evaluation.companyforcontact.dto.CompanyUpdateDTO;
import com.evaluation.companyforcontact.model.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    public Company saveCompany(Company requestCompany) throws Exception;

    public List<Company> allcompanies();

    public Company CompanyWithTvaNumber(Long tvaNumber);

    boolean tvaValidation(Long tvaNumber);



    void addContactToCompany(UUID uuidContact, Long tvaNumber);
    public Company getCompanyByUuid(UUID uuid);
    public Company editCompany(UUID uuid, CompanyUpdateDTO  requestUpdate);
}

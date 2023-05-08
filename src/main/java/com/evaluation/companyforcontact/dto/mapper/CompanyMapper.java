package com.evaluation.companyforcontact.dto.mapper;

import com.evaluation.companyforcontact.dto.CompanyCreateDTO;
import com.evaluation.companyforcontact.dto.ContactCreateDTO;
import com.evaluation.companyforcontact.model.Company;
import com.evaluation.companyforcontact.model.Contact;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CompanyMapper {

    public Company mapJsonToCompany(CompanyCreateDTO companyRequest){
            return Company.builder()
                    .uuid(UUID.randomUUID())
                    .tvaNumber(companyRequest.getTvaNumber())
                    .address(companyRequest.getAddress())
                    .build();
        }
}

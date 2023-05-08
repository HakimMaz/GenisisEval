package com.evaluation.companyforcontact.repository;

import com.evaluation.companyforcontact.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    public Company findByTvaNumber(Long tva);
    public Company findByUuid(UUID uuid);
}

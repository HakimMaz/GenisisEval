package com.evaluation.companyforcontact.repository;

import com.evaluation.companyforcontact.model.CompanyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyContactRepository extends JpaRepository<CompanyContact,Long> {
}

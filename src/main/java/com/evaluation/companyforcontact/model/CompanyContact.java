package com.evaluation.companyforcontact.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
public class CompanyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Contact contact;

    // constructors, getters, and setters

    public CompanyContact(){};
}
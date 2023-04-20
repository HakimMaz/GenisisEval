package com.evaluation.companyforcontact.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="contact")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany
    private List<Company> company;
    @Enumerated(EnumType.ORDINAL)
    private Type contactType;

    private Long tvaNumber;


    public void setTvaNumber(Long tvaNumber) {
        if(contactType.equals(Type.FREELANCER))
        this.tvaNumber = tvaNumber;
    }
}

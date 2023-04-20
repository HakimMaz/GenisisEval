package com.evaluation.companyforcontact.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long tvaNumber;
    @Embedded
    private Address address;



}

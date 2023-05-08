package com.evaluation.companyforcontact.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Contact {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "uuid", updatable = false, unique = true, length = 36)
        private UUID uuid ;

    private String firstName;

    private String lastName;
    @OneToMany(mappedBy = "contact")
    private Set<CompanyContact> companyContacts = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Type contactType;

    @Embedded
    private Address address;
   @Setter
    private Long tvaNumber;









    public void setTvaNumber(Long tvaNumber) {
        if(contactType.equals(Type.FREELANCER))
        this.tvaNumber = tvaNumber;
    }

    public Long getTvaNumber() {
        return tvaNumber;
    }
}

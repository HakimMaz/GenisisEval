package com.evaluation.companyforcontact.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.List;
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
    @OneToMany
    private List<Company> company;
    @Enumerated(EnumType.STRING)
    private Type contactType;

    private Long tvaNumber;







   /* public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Company> getCompany() {
        return company;
    }

    public void setCompany(List<Company> company) {
        this.company = company;
    }

    public Type getContactType() {
        return contactType;
    }

    public void setContactType(Type contactType) {
        this.contactType = contactType;
    }*/

    public void setTvaNumber(Long tvaNumber) {
        if(contactType.equals(Type.FREELANCER))
        this.tvaNumber = tvaNumber;
    }

    public Long getTvaNumber() {
        return tvaNumber;
    }
}

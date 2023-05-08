package com.evaluation.companyforcontact.dto;

import com.evaluation.companyforcontact.model.Address;
import com.evaluation.companyforcontact.model.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor(staticName ="build")
@NoArgsConstructor
@Data
public class ContactCreateDTO {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("firstName")
   private String firstName;
    @JsonProperty("lastName")
   private String lastName;
    @JsonProperty("contactType")
    private Type contactType;

    @JsonProperty("address")
    private Address address;
    @JsonProperty("tvaNumber")
    private Long tvaNumber;



}

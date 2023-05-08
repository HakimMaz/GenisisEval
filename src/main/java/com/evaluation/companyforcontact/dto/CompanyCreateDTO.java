package com.evaluation.companyforcontact.dto;

import com.evaluation.companyforcontact.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(staticName ="build")
@NoArgsConstructor
@Data
public class CompanyCreateDTO {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("tvaNumber")
    private Long tvaNumber;
    @JsonProperty("address")
    private Address address;
}

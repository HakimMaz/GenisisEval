package com.evaluation.companyforcontact.dto;

import com.evaluation.companyforcontact.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyUpdateDTO {
    @JsonProperty("tvaNumber")
       private Long tvaNumber;
       @JsonProperty("address")
       private Address address;
}

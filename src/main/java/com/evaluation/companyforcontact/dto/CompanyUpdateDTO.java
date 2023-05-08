package com.evaluation.companyforcontact.dto;

import com.evaluation.companyforcontact.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyUpdateDTO {
    @JsonProperty("tvaNumber")
       private Long tvaNumber;
       @JsonProperty("address")
       private Address address;
}

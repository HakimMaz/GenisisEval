package com.evaluation.companyforcontact.Controller;

import com.evaluation.companyforcontact.dto.CompanyCreateDTO;
import com.evaluation.companyforcontact.dto.CompanyUpdateDTO;
import com.evaluation.companyforcontact.dto.ContactCreateDTO;
import com.evaluation.companyforcontact.dto.mapper.CompanyMapper;
import com.evaluation.companyforcontact.exception.CompanyNotFoundException;
import com.evaluation.companyforcontact.model.Company;
import com.evaluation.companyforcontact.model.Contact;
import com.evaluation.companyforcontact.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper mapper;
    @ApiOperation(value = "Create new company", nickname = "createCompany", notes = "Allow user to create company", response = Company.class, tags = {"company"})
       @ApiResponses(value = {
               @ApiResponse(code = 200, message = "Company Created Successfully !", response = Company.class),
               @ApiResponse(code = 400, message = "Bad request!"),
               @ApiResponse(code = 500, message = "Internal server error!")
       })
    @PostMapping("/create")
    public ResponseEntity<?> create( @RequestBody CompanyCreateDTO companyRequest)  {

            try {
                Company company = mapper.mapJsonToCompany(companyRequest);
                var savedCompany = companyService.saveCompany(company);
                return ResponseEntity.ok()
                                        .body(java.util.Map.of(
                                                "data",savedCompany,
                                                "message","Company Created Successfully"));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(java.util.Map.of(
                                                "error","Internal Server Error",
                                                "message","Error while creating  a company"));
            }
        }
    @GetMapping("/all")
    public ResponseEntity<?> getAllCompanies(){
        return ResponseEntity.ok()
                .body(java.util.Map.of(
                        "data",companyService.allcompanies(),
                        "numbers of records",companyService.allcompanies().size(),
                        "message","companies retrieved successfully"
                ));
    }
    @GetMapping("/GetCompanyWithTva/{tvaNumber}")
    public ResponseEntity<?> getCompanyWithTva(@PathVariable Long tvaNumber){
        try {
                    Company company = companyService.CompanyWithTvaNumber(tvaNumber);

                    return ResponseEntity.ok()
                            .body(java.util.Map.of(
                                    "status",HttpStatus.FOUND,
                                    "message","Company retrieved successfully",
                                    "data",company
                 ));
                } catch (CompanyNotFoundException e) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(java.util.Map.of(
                                    "status",HttpStatus.NOT_FOUND,
                                    "message","Company not found with given Tva "+tvaNumber
                                                                         ));
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(java.util.Map.of(
                                        "status",HttpStatus.INTERNAL_SERVER_ERROR
                                             ));
                }


    }
    @PostMapping("/addContactToCompany/{uuidContact}/{tvaNumber}")
      public ResponseEntity<?> addContactToCompany(@PathVariable UUID uuidContact, @PathVariable  Long tvaNumber){
          try {
               companyService.addContactToCompany(uuidContact,tvaNumber);
               return   ResponseEntity.ok()
                       .body(java.util.Map.of(
                               "status",HttpStatus.ACCEPTED,
                               "message","Contact "+uuidContact+ " added to the company successfully"
                       ));
               } catch (Exception e) {
                           e.printStackTrace();
                           return ResponseEntity
                                                .status(HttpStatus.BAD_REQUEST)
                                                .body(java.util.Map.of(
                                                          "status",HttpStatus.BAD_REQUEST,
                                                          "message","Error during fetch company or contact"
                                                  ));
                       }
      }
      @PutMapping("/edit/{uuid}")
      public ResponseEntity<?> edit(@PathVariable  UUID uuid, @RequestBody CompanyUpdateDTO requestUpdate){
          try {
              companyService.editCompany(uuid,requestUpdate);
              return   ResponseEntity.ok()
                         .body(java.util.Map.of(
                                 "status",HttpStatus.ACCEPTED
                                 ));
                         } catch (Exception e) {
                                     e.printStackTrace();
                                     return ResponseEntity
                                                          .status(HttpStatus.BAD_REQUEST)
                                                          .body(java.util.Map.of(
                                                                    "status",HttpStatus.BAD_REQUEST
                                                            ));
                                 }
      }



}

package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.entity.Business;
import com.lakshan.carbonwise.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/businesses")
public class BusinessController {

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping("/addBusiness")
    public ResponseEntity<Business> addBusiness(@RequestBody Business business) {
        businessService.addNewBusiness(business);
        return new ResponseEntity<>(business, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Business getBusinessById(@PathVariable int id){
        return businessService.getBusiness(id);
    }

    @GetMapping
    public List<Business> getAllBusiness(){
        return businessService.getAllBusinesses();
    }

    @PutMapping("/updateBusiness")
    public void updateBusiness(@RequestBody Business business) {
        businessService.updateBusiness(business);
    }

    @DeleteMapping("{id}")
    public void deleteBusiness(@PathVariable int id) {
        businessService.deleteBusiness(id);
    }
}

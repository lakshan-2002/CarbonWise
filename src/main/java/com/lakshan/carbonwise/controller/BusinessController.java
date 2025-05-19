package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.entity.Business;
import com.lakshan.carbonwise.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    public void addBusiness(@RequestBody Business business) {
        businessService.addNewBusiness(business);
    }

    @GetMapping("{id}")
    public Business getBusinessById(@PathVariable int id){
        return businessService.getBusiness(id);
    }

    @GetMapping
    public List<Business> getAllBusiness(){
        return businessService.getAllBusinesses();
    }

    @PutMapping
    public void updateBusiness(@RequestBody Business business) {
        businessService.updateBusiness(business);
    }

    @DeleteMapping("{id}")
    public void deleteBusiness(@PathVariable int id) {
        businessService.deleteBusiness(id);
    }
}

package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.Business;
import com.lakshan.carbonwise.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public void addNewBusiness(Business business) {
        businessRepository.save(business);
    }

    public Business getBusiness(int id) {
        return businessRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
    }

    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    public void updateBusiness(Business business) throws RuntimeException{
        if(businessRepository.existsById(business.getId()))
            businessRepository.save(business);
        else
            throw new RuntimeException(business.getId() + " not found");
    }

    public void deleteBusiness(int id) throws RuntimeException{
        if(businessRepository.existsById(id))
            businessRepository.deleteById(id);
        else
            throw new RuntimeException(id + " not found");
    }
}

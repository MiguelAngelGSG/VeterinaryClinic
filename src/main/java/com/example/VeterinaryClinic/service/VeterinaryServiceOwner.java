package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinaryServiceOwner {

    @Autowired
    IVeterinaryRepositoryOwner iVeterinaryRepositoryOwner;

    public Owner createOwner(Owner newOwner){
       return iVeterinaryRepositoryOwner.save(newOwner);
    }
    
     public void updateOwner(Long id, Owner newOwner) {
        newOwner.setId(id);
        iVeterinaryRepositoryOwner.save(newOwner);
    }
}
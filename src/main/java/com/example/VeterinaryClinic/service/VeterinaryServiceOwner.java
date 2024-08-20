package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinaryServiceOwner {

    @Autowired
    IVeterinaryRepositoryOwner iVeterinaryRepositoryOwner;

    public Owner createOwner(Owner newOwner){
       return iVeterinaryRepositoryOwner.save(newOwner);
    }


    public  List<Owner> getAllOwners() {
        return (List<Owner>) iVeterinaryRepositoryOwner.findAll();
    }

    public void updateOwner(Long id, Owner newOwner) {
        newOwner.setId(id);
        iVeterinaryRepositoryOwner.save(newOwner);
    }

    public void deleteOwner(Long id) {
        iVeterinaryRepositoryOwner.deleteById(id);
    }


}
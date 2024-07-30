package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinaryServicePet {

    @Autowired
    IVeterinaryRepositoryPet iVeterinaryRepositoryPet;

    public Pet createPet(Pet newPet){
        iVeterinaryRepositoryPet.save(newPet);
        return newPet;
    }
    public void updatePet(Long id, Pet newPet) {
        newPet.setId(id);
        iVeterinaryRepositoryPet.save(newPet);
    }
}
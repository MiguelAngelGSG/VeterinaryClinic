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

    public Boolean deletePet(Pet deletePet){
        iVeterinaryRepositoryPet.delete(deletePet);

        if (iVeterinaryRepositoryPet.deleteById(id)) {
            return true;
        }
        return false;
    }

}
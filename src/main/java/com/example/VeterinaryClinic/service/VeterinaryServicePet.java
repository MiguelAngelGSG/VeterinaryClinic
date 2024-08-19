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
       return iVeterinaryRepositoryPet.save(newPet);
    }

    public String deletePet(Long id){
        try{
            iVeterinaryRepositoryPet.deleteById(id);
            return "Delete Pet";
        }catch (Exception error){
        return "Error to delete Pet";
    }
    
            
    }

}
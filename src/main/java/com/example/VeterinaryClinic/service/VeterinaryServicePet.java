package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Pet;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VeterinaryServicePet {

    @Autowired
    IVeterinaryRepositoryPet iVeterinaryRepositoryPet;





    public List<Pet> getAllPets() {
        return (List<Pet>) iVeterinaryRepositoryPet.findAll();
    }


}
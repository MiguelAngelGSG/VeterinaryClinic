package com.example.VeterinaryClinic.controller;

import com.example.VeterinaryClinic.service.VeterinaryServicePet;
import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")

public class VeterinaryControllerPet {

    @Autowired
    VeterinaryServicePet veterinaryServicePet;

    @PostMapping(path = "pet")
    public Pet createPet(@RequestBody Pet newPet) {
        return veterinaryServicePet.createPet(newPet);
    }
     @PutMapping(path = "/pet/{id}")
    public void updatePet(@RequestBody Pet pet,
    @PathVariable Long id) {
        veterinaryServicePet.updatePet(id, pet);
    }
}
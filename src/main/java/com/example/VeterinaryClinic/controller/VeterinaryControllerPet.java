package com.example.VeterinaryClinic.controller;

import com.example.VeterinaryClinic.service.VeterinaryServicePet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.VeterinaryClinic.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")

public class VeterinaryControllerPet {

    @Autowired
    VeterinaryServicePet veterinaryServicePet;

    @PostMapping(path = "/pet")
    public Pet createPet(@RequestBody Pet newPet) {
        return veterinaryServicePet.createPet(newPet);
    }

    @GetMapping(path ="/pet")
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = veterinaryServicePet.getAllPets();
        if (pets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @PutMapping(path = "/pet/{id}")
    public void updatePet(@RequestBody Pet pet, @PathVariable Long id) {
        veterinaryServicePet.updatePet(id, pet);
    }

    @DeleteMapping(path = "/pet/{id}")
    public void deletePet(@PathVariable("id") Long id) {
        veterinaryServicePet.deletePet(id);
    }




}
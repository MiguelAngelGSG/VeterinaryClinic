package com.example.VeterinaryClinic.controller;


import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.service.VeterinaryServiceOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")


public class VeterinaryControllerOwner {

    @Autowired
    VeterinaryServiceOwner veterinaryServiceOwner;








    @GetMapping(path ="/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> Owners = veterinaryServiceOwner.getAllOwners();
        if (Owners.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Owners, HttpStatus.OK);
    }

}



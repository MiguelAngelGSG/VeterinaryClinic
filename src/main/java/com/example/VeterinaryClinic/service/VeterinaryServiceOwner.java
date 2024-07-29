package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Owner;

import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryOwner;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinaryServiceOwner {

    @Autowired
    IVeterinaryRepositoryOwner iVeterinaryRepositoryOwner;







    public  List<Owner> getAllOwners() {
        return (List<Owner>) iVeterinaryRepositoryOwner.findAll();
    }




}
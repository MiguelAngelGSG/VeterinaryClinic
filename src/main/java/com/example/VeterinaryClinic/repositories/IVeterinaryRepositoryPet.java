package com.example.VeterinaryClinic.repositories;

import com.example.VeterinaryClinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface IVeterinaryRepositoryPet extends CrudRepository<Pet, Long > {
}
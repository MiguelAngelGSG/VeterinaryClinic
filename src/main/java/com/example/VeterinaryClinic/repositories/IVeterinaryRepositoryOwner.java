package com.example.VeterinaryClinic.repositories;

import com.example.VeterinaryClinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface IVeterinaryRepositoryOwner extends CrudRepository <Owner, Long>{
}
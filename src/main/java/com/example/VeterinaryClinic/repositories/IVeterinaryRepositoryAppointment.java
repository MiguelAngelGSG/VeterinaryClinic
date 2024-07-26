package com.example.VeterinaryClinic.repositories;

import com.example.VeterinaryClinic.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface IVeterinaryRepositoryAppointment extends CrudRepository <Appointment, Long> {
}
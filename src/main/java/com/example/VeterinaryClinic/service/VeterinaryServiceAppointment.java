package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VeterinaryServiceAppointment {

    @Autowired
    IVeterinaryRepositoryAppointment iVeterinaryRepositoryAppointment;

    public Appointment createAppointment(Appointment newAppointment){
        iVeterinaryRepositoryAppointment.save(newAppointment);
        return newAppointment;
    }
    public void updateAppointment(Long id, Appointment newAppointment) {
        newAppointment.setId(id);
        iVeterinaryRepositoryAppointment.save(newAppointment);
    }
    public List<Appointment> getAllAppointment() {
        return (List<Appointment>) iVeterinaryRepositoryAppointment.findAll();
    }
}
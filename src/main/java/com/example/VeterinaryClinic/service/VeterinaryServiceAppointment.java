package com.example.VeterinaryClinic.service;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinaryServiceAppointment {

    @Autowired
    IVeterinaryRepositoryAppointment iVeterinaryRepositoryAppointment;

    public Appointment createAppointment(Appointment newAppointment) {
        iVeterinaryRepositoryAppointment.save(newAppointment);
        return newAppointment;
    }

    public Long deleteAppointment(Long deleteAppointmentId){
        iVeterinaryRepositoryAppointment.deleteById(deleteAppointmentId);
        return deleteAppointmentId;
    }
}
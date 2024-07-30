package com.example.VeterinaryClinic.controller;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.service.VeterinaryServiceAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")

public class VeterinaryControllerAppointment {

    @Autowired
    VeterinaryServiceAppointment veterinaryServiceAppointment;

    @PostMapping(path = "/api/appointment")
    public Appointment createAppointment(@RequestBody Appointment newAppointment) {
        return veterinaryServiceAppointment.createAppointment(newAppointment);
    }
}

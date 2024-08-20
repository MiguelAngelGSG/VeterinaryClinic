package com.example.VeterinaryClinic.controller;

import com.example.VeterinaryClinic.model.Appointment;
import com.example.VeterinaryClinic.service.VeterinaryServiceAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")

public class VeterinaryControllerAppointment {

    @Autowired
    VeterinaryServiceAppointment veterinaryServiceAppointment;

    @PostMapping(path = "/appointment")
    public Appointment createAppointment(@RequestBody Appointment newAppointment) {
        return veterinaryServiceAppointment.createAppointment(newAppointment);
    }

    @GetMapping(path ="/appointment")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> Appointments = veterinaryServiceAppointment.getAlLAppointments();
        if (Appointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Appointments, HttpStatus.OK);
    }

    @PutMapping(path = "/appointment/{id}")
    public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id) {
        veterinaryServiceAppointment.updateAppointment(id, appointment);
    }

    @DeleteMapping(path = "/appointment/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Long id) {
        veterinaryServiceAppointment.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }



}

package com.example.VeterinaryClinic.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import com.example.VeterinaryClinic.repositories.IVeterinaryRepositoryAppointment;
import com.example.VeterinaryClinic.service.VeterinaryServiceAppointment;

public class VeterinaryControllerAppointmentTest {


    @MockBean
    private VeterinaryServiceAppointment  veterinaryServiceAppointment;

    @Mock
    private IVeterinaryRepositoryAppointment iVeterinaryRepositoryAppointment;
    @Autowired
    private MockMvc mockMvc;


@Test

public void deleteAppointmentById() throws Exception {
    long id = 1L;

    mockMvc.perform(delete("/appointment/{id}", id)).andExpect(status().isOk());

    verify(veterinaryServiceAppointment).deleteAppointment(id);
} 
}
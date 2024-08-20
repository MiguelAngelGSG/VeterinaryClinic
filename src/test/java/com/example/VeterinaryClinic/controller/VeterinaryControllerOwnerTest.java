package com.example.VeterinaryClinic.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.service.VeterinaryServiceOwner;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

class VeterinaryControllerOwnerTest {

    private MockMvc mockMvc;
    private Owner newOwner;
    private Owner anotherOwner;

    @Mock
    private VeterinaryServiceOwner veterinaryServiceOwner;

    @InjectMocks
    private VeterinaryControllerOwner veterinaryControllerOwner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veterinaryControllerOwner).build();

        newOwner = new Owner();
        newOwner.setId(1L);
        newOwner.setName("Miguel");
        newOwner.setPhone("+34 1234567890");
        newOwner.setEmail("example@example.com");

        anotherOwner = new Owner();
        anotherOwner.setId(2L);
        anotherOwner.setName("Isabel");
        anotherOwner.setPhone("+34 0987654321");
        anotherOwner.setEmail("testing@example.com");
    }

    @Test
    void testCreateOwner() throws Exception {
        when(veterinaryServiceOwner.createOwner(any(Owner.class))).thenReturn(newOwner);

        mockMvc.perform(post("/owner")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(newOwner)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Miguel"))
                .andExpect(jsonPath("$.phone").value("+34 1234567890"))
                .andExpect(jsonPath("$.email").value("example@example.com"));

        verify(veterinaryServiceOwner, times(1)).createOwner(any(Owner.class));
    }

    @Test
    void testCreateOwnerWithDifferentDetails() throws Exception {
        when(veterinaryServiceOwner.createOwner(any(Owner.class))).thenReturn(anotherOwner);

        mockMvc.perform(post("/owner")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(anotherOwner)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Isabel"))
                .andExpect(jsonPath("$.phone").value("+34 0987654321"))
                .andExpect(jsonPath("$.email").value("testing@example.com"));

        verify(veterinaryServiceOwner, times(1)).createOwner(any(Owner.class));
    }

    @Test
    void testGetAllOwners() throws Exception {
        List<Owner> owners = Arrays.asList(newOwner, anotherOwner);
        when(veterinaryServiceOwner.getAllOwners()).thenReturn(owners);

        mockMvc.perform(get("/owner")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Miguel"))
                .andExpect(jsonPath("$[0].phone").value("+34 1234567890"))
                .andExpect(jsonPath("$[0].email").value("example@example.com"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Isabel"))
                .andExpect(jsonPath("$[1].phone").value("+34 0987654321"))
                .andExpect(jsonPath("$[1].email").value("testing@example.com"));

        verify(veterinaryServiceOwner, times(1)).getAllOwners();
    }
}


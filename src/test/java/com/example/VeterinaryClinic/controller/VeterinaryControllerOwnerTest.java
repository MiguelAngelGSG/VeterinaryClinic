package com.example.VeterinaryClinic.controller;

import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.service.VeterinaryServiceOwner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(VeterinaryControllerOwner.class)
public class VeterinaryControllerOwnerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeterinaryServiceOwner veterinaryServiceOwner;

    private List<Owner> ownersList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ownersList = new ArrayList<>();

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("John Doe");
        owner1.setPhone("123-456-7890");
        owner1.setEmail("john.doe@example.com");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Jane Smith");
        owner2.setPhone("098-765-4321");
        owner2.setEmail("jane.smith@example.com");

        ownersList.add(owner1);
        ownersList.add(owner2);
    }

    @Test
    void getAllOwners() throws Exception {
        when(veterinaryServiceOwner.getAllOwners()).thenReturn(ownersList);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")));
    }
}

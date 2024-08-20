package com.example.VeterinaryClinic.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

class VeterinaryControllerOwnerTest {

    private MockMvc mockMvc;
    private Owner newOwner;

    @Mock
    private VeterinaryServiceOwner veterinaryServiceOwner;

    @InjectMocks
    private VeterinaryControllerOwner veterinaryControllerOwner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veterinaryControllerOwner).build();

        // Initialize the owner object here
        newOwner = new Owner();
        newOwner.setId(1L);
        newOwner.setName("Miguel");
        newOwner.setPhone("+34 1234567890");
        newOwner.setEmail("example@example.com");
    }

    @Test
    void testCreateOwner() throws Exception {
        // Arrange
        when(veterinaryServiceOwner.createOwner(any(Owner.class))).thenReturn(newOwner);

        // Act & Assert
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
        // Arrange
        // Modify the owner details for this test
        newOwner.setId(2L);
        newOwner.setName("Isabel");
        newOwner.setPhone("+34 0987654321");
        newOwner.setEmail("testing@example.com");

        when(veterinaryServiceOwner.createOwner(any(Owner.class))).thenReturn(newOwner);

        // Act & Assert
        mockMvc.perform(post("/owner")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(newOwner)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Isabel"))
                .andExpect(jsonPath("$.phone").value("+34 09876543214"))
                .andExpect(jsonPath("$.email").value("testing@example.com"));

        verify(veterinaryServiceOwner, times(1)).createOwner(any(Owner.class));
    }

    @Test
void testUpdateOwner() throws Exception {

    Owner updateOwner = new Owner();
    updateOwner.setId(2L);
    updateOwner.setName("Isabel");
    updateOwner.setPhone("+34 09876543214");
    updateOwner.setEmail("testing@example.com");

    doNothing().when(veterinaryServiceOwner).updateOwner(updateOwner,2L);

    mockMvc.perform(put("/owner/2")
                    .contentType("application/json")
                    .content("{\"id\":2,\"name\":\"Isabel\",\"email\":\"testing@example.com\",\"phone\":\"+34 09876543214\"}"))
            .andExpect(status().isOk());

}

}
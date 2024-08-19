package com.example.VeterinaryClinic.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

class VeterinaryControllerOwnerTest {

    private MockMvc mockMvc;

    @Mock
    private VeterinaryServiceOwner veterinaryServiceOwner;

    @InjectMocks
    private VeterinaryControllerOwner veterinaryControllerOwner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veterinaryControllerOwner).build();
    }

    @Test
    void testCreateOwner() throws Exception {
        // Arrange
        Owner newOwner = new Owner();
        newOwner.setId(1L);
        newOwner.setName("Miguel");
        newOwner.setPhone("+34 1234567890");
        newOwner.setEmail("example@example.com");

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
    void testDeleteOwner() throws Exception {
        // Arrange
        Long ownerId = 1L;

        // Mock the service to do nothing when deleteOwner is called
        doNothing().when(veterinaryServiceOwner).deleteOwner(ownerId);

        // Act & Assert
        mockMvc.perform(delete("/owner/{id}", ownerId))
               .andExpect(status().isOk());  // Or .andExpect(status().isNoContent());

        // Verify that the service's deleteOwner method was called once
        verify(veterinaryServiceOwner, times(1)).deleteOwner(ownerId);
    }
}

package com.example.VeterinaryClinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "date_time")
    private String dateTime;

    @Column(name = "name")
    private String name;

    @Column(name = "consultation_type")
    private String consultationType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
}

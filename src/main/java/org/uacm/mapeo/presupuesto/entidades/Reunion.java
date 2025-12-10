package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "reunion")
public class Reunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReunion;

    private String asunto;

    private LocalDate fecha;

    private LocalTime hora;

    private String lugar;
}

package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "cronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCronograma;

    private String nombre;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}

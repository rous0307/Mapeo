package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data

public class proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idProyecto;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalEstimada;

}

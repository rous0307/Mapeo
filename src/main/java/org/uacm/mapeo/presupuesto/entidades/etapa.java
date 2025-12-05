package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEtapa;
    private String id_Proyecto;
    private String fase;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

}

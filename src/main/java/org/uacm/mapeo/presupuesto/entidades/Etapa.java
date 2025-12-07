package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "etapa")
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEtapa;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    private String fase;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @OneToOne(mappedBy = "etapa", cascade = CascadeType.ALL)
    private Presupuesto presupuesto;
}

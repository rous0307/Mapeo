package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;

@Entity
@Table(name = "etapa")
@Data
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEtapa;

    private String fase;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    @JsonBackReference
    private Proyecto proyecto;

    @OneToOne(mappedBy = "etapa", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Presupuesto presupuesto;
}

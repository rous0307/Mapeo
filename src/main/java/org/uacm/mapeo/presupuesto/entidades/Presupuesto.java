package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "presupuesto")
@Data
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPresupuesto;

    private float montoTotalEstimado;

    private float montoUtilizado;

    @OneToOne
    @JoinColumn(name = "id_etapa", unique = true, nullable = false)
    @JsonBackReference
    private Etapa etapa;
}

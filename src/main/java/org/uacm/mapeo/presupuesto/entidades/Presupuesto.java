package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "presupuesto")
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPresupuesto;

    @OneToOne
    @JoinColumn(name = "id_etapa", unique = true, nullable = false)
    private Etapa etapa;

    private float montoTotalEstimado;

    private float montoUtilizado;
}

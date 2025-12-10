package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "contrato_proyecto")
public class ContratoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContrato;

    private String descripcion;

    private double monto;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;
}

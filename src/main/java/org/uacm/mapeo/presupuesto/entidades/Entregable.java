package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "entregable")
public class Entregable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntregable;

    private String nombre;

    private String descripcion;

    private String estado;
}

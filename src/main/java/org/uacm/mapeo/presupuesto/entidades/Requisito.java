package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "requisito")
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRequisito;

    private String nombre;

    private String tipo;

    private String descripcion;
}

package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartamento;

    private String nombre;
}

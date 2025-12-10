package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    private String nombre;

    private String correo;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;
}

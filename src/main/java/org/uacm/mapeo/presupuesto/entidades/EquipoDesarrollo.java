package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "equipo_desarrollo")
public class EquipoDesarrollo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEquipo;

    @ManyToOne
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;
}

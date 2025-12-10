package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    private String nombre;

    private String telefono;

    private String correo;
}

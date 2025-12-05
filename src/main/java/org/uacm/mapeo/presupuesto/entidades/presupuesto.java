package org.uacm.mapeo.presupuesto.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresupuesto;
    private  int idEtapa;
    private float montoTotalEstimado;
    private float montoUtilizado;


}

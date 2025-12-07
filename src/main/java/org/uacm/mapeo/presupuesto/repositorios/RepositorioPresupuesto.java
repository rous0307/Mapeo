package org.uacm.mapeo.presupuesto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.uacm.mapeo.presupuesto.entidades.Presupuesto;

import java.util.List;

@RepositoryRestResource(
        path = "presupuestos",
        collectionResourceRel = "presupuestos"
)
public interface RepositorioPresupuesto extends JpaRepository<Presupuesto, Integer> {

    @RestResource(path = "por-etapa", rel = "buscarPorEtapa")
    Presupuesto findByEtapa_IdEtapa(int idEtapa);

    @RestResource(path = "monto-mayor", rel = "presupuestoMayor")
    List<Presupuesto> findByMontoTotalEstimadoGreaterThan(double monto);

}

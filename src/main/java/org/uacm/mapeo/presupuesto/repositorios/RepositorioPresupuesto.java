package org.uacm.mapeo.presupuesto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.uacm.mapeo.presupuesto.entidades.Presupuesto;

@RepositoryRestResource(
        path = "presupuestos",
        collectionResourceRel = "presupuestos"
)
public interface RepositorioPresupuesto
        extends JpaRepository<Presupuesto, Integer> {
}

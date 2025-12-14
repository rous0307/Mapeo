package org.uacm.mapeo.presupuesto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.uacm.mapeo.presupuesto.entidades.Etapa;

@RepositoryRestResource(
        path = "etapas",
        collectionResourceRel = "etapas"
)
public interface RepositorioEtapa
        extends JpaRepository<Etapa, Integer> {
}

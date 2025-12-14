package org.uacm.mapeo.presupuesto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.uacm.mapeo.presupuesto.entidades.Proyecto;

@RepositoryRestResource(
        path = "proyectos",
        collectionResourceRel = "proyectos"
)
public interface RepositorioProyecto
        extends JpaRepository<Proyecto, Integer> {
}

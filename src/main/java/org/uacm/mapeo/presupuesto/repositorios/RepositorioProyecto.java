package org.uacm.mapeo.presupuesto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.uacm.mapeo.presupuesto.entidades.Proyecto;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(
        path = "proyectos",
        collectionResourceRel = "proyectos"
)
public interface RepositorioProyecto extends JpaRepository<Proyecto, Integer> {

    // Buscar por nombre que contenga texto
    @RestResource(path = "por-nombre", rel = "buscarNombre")
    List<Proyecto> findByNombreContaining(String nombre);

    // Buscar proyectos con fechaInicio después de cierta fecha
    @RestResource(path = "despues-de", rel = "proyectosRecientes")
    List<Proyecto> findByFechaInicioAfter(LocalDate fecha);
}

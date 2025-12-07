package org.uacm.mapeo.presupuesto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.uacm.mapeo.presupuesto.entidades.Etapa;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(
        path = "etapas",
        collectionResourceRel = "etapas"
)
public interface RepositorioEtapa extends JpaRepository<Etapa, Integer> {

    @RestResource(path = "por-fase", rel = "buscarFase")
    List<Etapa> findByFaseContaining(String fase);

    @RestResource(path = "por-proyecto", rel = "etapasPorProyecto")
    List<Etapa> findByProyecto_IdProyecto(int idProyecto);

    @RestResource(path = "antes-de", rel = "etapasAntes")
    List<Etapa> findByFechaInicioBefore(LocalDate fecha);
}

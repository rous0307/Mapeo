package org.uacm.mapeo.presupuesto.servicios;

import org.springframework.stereotype.Service;
import org.uacm.mapeo.presupuesto.entidades.Etapa;
import org.uacm.mapeo.presupuesto.repositorios.RepositorioEtapa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioEtapa {

    private final RepositorioEtapa repo;

    public ServicioEtapa(RepositorioEtapa repo) {
        this.repo = repo;
    }

    public Etapa guardar(Etapa etapa) {
        return repo.save(etapa);
    }

    public List<Etapa> listar() {
        return repo.findAll();
    }

    public Optional<Etapa> buscarPorId(int id) {
        return repo.findById(id);
    }

    public List<Etapa> buscarPorFase(String fase) {
        return repo.findByFaseContaining(fase);
    }

    public List<Etapa> buscarPorProyecto(int idProyecto) {
        return repo.findByProyecto_IdProyecto(idProyecto);
    }

    public List<Etapa> buscarAntesDe(LocalDate fecha) {
        return repo.findByFechaInicioBefore(fecha);
    }

    public void eliminar(int id) {
        repo.deleteById(id);
    }
}

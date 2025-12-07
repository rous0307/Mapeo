package org.uacm.mapeo.presupuesto.servicios;

import org.springframework.stereotype.Service;
import org.uacm.mapeo.presupuesto.entidades.Presupuesto;
import org.uacm.mapeo.presupuesto.repositorios.RepositorioPresupuesto;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPresupuesto {

    private final RepositorioPresupuesto repo;

    public ServicioPresupuesto(RepositorioPresupuesto repo) {
        this.repo = repo;
    }

    public Presupuesto guardar(Presupuesto p) {

        if (p.getMontoUtilizado() > p.getMontoTotalEstimado()) {
            throw new IllegalArgumentException("El monto utilizado no puede exceder el monto estimado.");
        }

        return repo.save(p);
    }

    public List<Presupuesto> listar() {
        return repo.findAll();
    }

    public Optional<Presupuesto> buscarPorId(int id) {
        return repo.findById(id);
    }

    public Presupuesto buscarPorEtapa(int idEtapa) {
        return repo.findByEtapa_IdEtapa(idEtapa);
    }

    public List<Presupuesto> buscarMayorA(float monto) {
        return repo.findByMontoTotalEstimadoGreaterThan(monto);
    }

    public void eliminar(int id) {
        repo.deleteById(id);
    }
}

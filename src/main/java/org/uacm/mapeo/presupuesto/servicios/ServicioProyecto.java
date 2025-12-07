package org.uacm.mapeo.presupuesto.servicios;

import org.springframework.stereotype.Service;
import org.uacm.mapeo.presupuesto.entidades.Proyecto;
import org.uacm.mapeo.presupuesto.repositorios.RepositorioProyecto;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProyecto {

    private final RepositorioProyecto repo;

    public ServicioProyecto(RepositorioProyecto repo) {
        this.repo = repo;
    }

    public Proyecto guardar(Proyecto proyecto) {
        return repo.save(proyecto);
    }

    public List<Proyecto> listar() {
        return repo.findAll();
    }

    public Optional<Proyecto> buscarPorId(int id) {
        return repo.findById(id);
    }

    public void eliminar(int id) {
        repo.deleteById(id);
    }

    public List<Proyecto> buscarPorNombre(String nombre) {
        return repo.findByNombreContaining(nombre);
    }
}

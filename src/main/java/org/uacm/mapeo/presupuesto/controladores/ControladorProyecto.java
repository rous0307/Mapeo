package org.uacm.mapeo.presupuesto.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uacm.mapeo.presupuesto.entidades.Proyecto;
import org.uacm.mapeo.presupuesto.servicios.ServicioProyecto;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ControladorProyecto {

    private final ServicioProyecto servicio;

    public ControladorProyecto(ServicioProyecto servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Proyecto> crear(@RequestBody Proyecto proyecto) {
        return ResponseEntity.ok(servicio.guardar(proyecto));
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Proyecto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(servicio.buscarPorNombre(nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

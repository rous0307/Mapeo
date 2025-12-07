package org.uacm.mapeo.presupuesto.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uacm.mapeo.presupuesto.entidades.Etapa;
import org.uacm.mapeo.presupuesto.servicios.ServicioEtapa;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/etapas")
public class ControladorEtapa {

    private final ServicioEtapa servicio;

    public ControladorEtapa(ServicioEtapa servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Etapa> crear(@RequestBody Etapa etapa) {
        return ResponseEntity.ok(servicio.guardar(etapa));
    }

    @GetMapping
    public ResponseEntity<List<Etapa>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etapa> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-fase")
    public ResponseEntity<List<Etapa>> buscarPorFase(@RequestParam String fase) {
        return ResponseEntity.ok(servicio.buscarPorFase(fase));
    }

    @GetMapping("/por-proyecto/{idProyecto}")
    public ResponseEntity<List<Etapa>> buscarPorProyecto(@PathVariable int idProyecto) {
        return ResponseEntity.ok(servicio.buscarPorProyecto(idProyecto));
    }

    @GetMapping("/antes-de")
    public ResponseEntity<List<Etapa>> buscarAntesDe(@RequestParam String fecha) {
        LocalDate f = LocalDate.parse(fecha);
        return ResponseEntity.ok(servicio.buscarAntesDe(f));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

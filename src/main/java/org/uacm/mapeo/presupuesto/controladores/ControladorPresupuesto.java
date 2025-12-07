package org.uacm.mapeo.presupuesto.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uacm.mapeo.presupuesto.entidades.Presupuesto;
import org.uacm.mapeo.presupuesto.servicios.ServicioPresupuesto;

import java.util.List;

@RestController
@RequestMapping("/api/presupuestos")
public class ControladorPresupuesto {

    private final ServicioPresupuesto servicio;

    public ControladorPresupuesto(ServicioPresupuesto servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Presupuesto presupuesto) {
        try {
            return ResponseEntity.ok(servicio.guardar(presupuesto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Presupuesto>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presupuesto> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-etapa/{idEtapa}")
    public ResponseEntity<Presupuesto> buscarPorEtapa(@PathVariable int idEtapa) {
        Presupuesto p = servicio.buscarPorEtapa(idEtapa);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}/usar")
    public ResponseEntity<?> registrarUso(
            @PathVariable int id,
            @RequestParam float monto
    ) {
        var optional = servicio.buscarPorId(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        Presupuesto p = optional.get();

        float nuevoMonto = p.getMontoUtilizado() + monto;

        if (nuevoMonto > p.getMontoTotalEstimado()) {
            return ResponseEntity.badRequest().body("El uso excede el monto estimado");
        }

        p.setMontoUtilizado(nuevoMonto);
        servicio.guardar(p);

        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

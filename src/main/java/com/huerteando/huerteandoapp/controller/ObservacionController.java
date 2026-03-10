package com.huerteando.huerteandoapp.controller;
import com.huerteando.huerteandoapp.model.Observacion;
import com.huerteando.huerteandoapp.service.IObservacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Controlador REST para la gestión de observaciones.
 * Proporciona endpoints CRUD básicos.
 */
@RestController
@RequestMapping("/api/observaciones")
public class ObservacionController {
    private final IObservacionService observacionService;
    public ObservacionController(IObservacionService observacionService) {
        this.observacionService = observacionService;
    }
    /**
     * GET /api/observaciones
     * Lista todas las observaciones.
     * @return Lista de observaciones
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<Observacion>> listarTodas() {
        List<Observacion> observaciones = observacionService.listarTodas();
        return ResponseEntity.ok(observaciones);
    }
    /**
     * GET /api/observaciones/{id}
     * Obtiene una observación por su ID.
     * @param id ID de la observación
     * @return Observación encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Observacion> obtenerPorId(@PathVariable Long id) {
        Observacion observacion = observacionService.buscarPorId(id);
        if (observacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(observacion);
    }
    /**
     * POST /api/observaciones
     * Crea una nueva observación.
     * @param observacion Datos de la observación a crear
     * @return Observación creada con código 201
     */
    @PostMapping
    public ResponseEntity<Observacion> crear(@RequestBody Observacion observacion) {
        Observacion nuevaObservacion = observacionService.crear(observacion);
        if (nuevaObservacion == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaObservacion);
    }
    /**
     * PUT /api/observaciones/{id}
     * Actualiza una observación existente.
     * @param id ID de la observación a actualizar
     * @param observacion Datos actualizados
     * @return Observación actualizada o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Observacion> actualizar(@PathVariable Long id, @RequestBody Observacion observacion) {
        // Aseguramos que el ID del path coincida con el del body
        observacion.setId(id);
        Observacion observacionActualizada = observacionService.actualizar(observacion);
        if (observacionActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(observacionActualizada);
    }
    /**
     * DELETE /api/observaciones/{id}
     * Elimina una observación.
     * @param id ID de la observación a eliminar
     * @return 204 No Content si se eliminó correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Observacion observacion = observacionService.buscarPorId(id);
        if (observacion == null) {
            return ResponseEntity.notFound().build();
        }
        observacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

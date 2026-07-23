package com.adopcion.controller;

import com.adopcion.dto.PerroDTO;
import com.adopcion.model.Tamano;
import com.adopcion.service.PerroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perros")
public class PerroController {

    private final PerroService perroService;

    public PerroController(PerroService perroService) {
        this.perroService = perroService;
    }

    @GetMapping
    public ResponseEntity<List<PerroDTO>> listarTodos() {
        return ResponseEntity.ok(perroService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerroDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(perroService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PerroDTO> crearPerro(@Valid @RequestBody PerroDTO perroDTO) {
        return new ResponseEntity<>(perroService.crearPerro(perroDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerroDTO> actualizarPerro(@PathVariable Long id, @Valid @RequestBody PerroDTO perroDTO) {
        return ResponseEntity.ok(perroService.actualizarPerro(id, perroDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerro(@PathVariable Long id) {
        perroService.eliminarPerro(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/adoptar")
    public ResponseEntity<?> adoptarPerro(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(perroService.adoptarPerro(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoints de búsqueda
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<PerroDTO>> buscarPorNombre(@RequestParam String valor) {
        return ResponseEntity.ok(perroService.buscarPorNombre(valor));
    }

    @GetMapping("/buscar/raza")
    public ResponseEntity<List<PerroDTO>> buscarPorRaza(@RequestParam String valor) {
        return ResponseEntity.ok(perroService.buscarPorRaza(valor));
    }

    @GetMapping("/buscar/tamano")
    public ResponseEntity<List<PerroDTO>> buscarPorTamano(@RequestParam Tamano valor) {
        return ResponseEntity.ok(perroService.buscarPorTamano(valor));
    }
}
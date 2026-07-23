package com.adopcion.service;

import com.adopcion.dto.PerroDTO;
import com.adopcion.model.Tamano;
import java.util.List;

// Interfaz para definir el contrato (Polimorfismo / Abstracción)
public interface PerroService {
    List<PerroDTO> obtenerTodos();
    PerroDTO obtenerPorId(Long id);
    PerroDTO crearPerro(PerroDTO perroDTO);
    PerroDTO actualizarPerro(Long id, PerroDTO perroDTO);
    void eliminarPerro(Long id);
    PerroDTO adoptarPerro(Long id);
    
    // Búsquedas
    List<PerroDTO> buscarPorNombre(String nombre);
    List<PerroDTO> buscarPorRaza(String raza);
    List<PerroDTO> buscarPorTamano(Tamano tamano);
}
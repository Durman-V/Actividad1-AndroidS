package com.adopcion.service;

import com.adopcion.dto.PerroDTO;
import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.EstadoPerro;
import com.adopcion.model.Perro;
import com.adopcion.model.Tamano;
import com.adopcion.repository.PerroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerroServiceImpl implements PerroService {

    private final PerroRepository perroRepository;

    // Inyección de dependencias por constructor
    public PerroServiceImpl(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    @Override
    public List<PerroDTO> obtenerTodos() {
        return perroRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PerroDTO obtenerPorId(Long id) {
        Perro perro = perroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perro no encontrado con ID: " + id));
        return mapToDTO(perro);
    }

    @Override
    public PerroDTO crearPerro(PerroDTO dto) {
        Perro perro = mapToEntity(dto);
        if(perro.getEstado() == null) perro.setEstado(EstadoPerro.DISPONIBLE);
        Perro guardado = perroRepository.save(perro);
        return mapToDTO(guardado);
    }

    @Override
    public PerroDTO actualizarPerro(Long id, PerroDTO dto) {
        Perro perro = perroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perro no encontrado con ID: " + id));

        perro.setNombre(dto.getNombre());
        perro.setRaza(dto.getRaza());
        perro.setEdad(dto.getEdad());
        perro.setTamano(dto.getTamano());
        perro.setSexo(dto.getSexo());
        perro.setDescripcion(dto.getDescripcion());
        perro.setFotoUrl(dto.getFotoUrl());
        
        if (dto.getEstado() != null) {
            perro.setEstado(dto.getEstado());
        }

        return mapToDTO(perroRepository.save(perro));
    }

    @Override
    public void eliminarPerro(Long id) {
        Perro perro = perroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perro no encontrado con ID: " + id));
        perroRepository.delete(perro);
    }

    @Override
    public PerroDTO adoptarPerro(Long id) {
        Perro perro = perroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perro no encontrado con ID: " + id));
        
        if (perro.getEstado() == EstadoPerro.ADOPTADO) {
            throw new IllegalStateException("El perro ya ha sido adoptado.");
        }
        
        perro.setEstado(EstadoPerro.ADOPTADO);
        return mapToDTO(perroRepository.save(perro));
    }

    @Override
    public List<PerroDTO> buscarPorNombre(String nombre) {
        return perroRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PerroDTO> buscarPorRaza(String raza) {
        return perroRepository.findByRazaContainingIgnoreCase(raza).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PerroDTO> buscarPorTamano(Tamano tamano) {
        return perroRepository.findByTamano(tamano).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    // Métodos de mapeo internos (Abstracción de la conversión)
    private PerroDTO mapToDTO(Perro perro) {
        PerroDTO dto = new PerroDTO();
        dto.setId(perro.getId());
        dto.setNombre(perro.getNombre());
        dto.setRaza(perro.getRaza());
        dto.setEdad(perro.getEdad());
        dto.setTamano(perro.getTamano());
        dto.setSexo(perro.getSexo());
        dto.setDescripcion(perro.getDescripcion());
        dto.setFotoUrl(perro.getFotoUrl());
        dto.setEstado(perro.getEstado());
        return dto;
    }

    private Perro mapToEntity(PerroDTO dto) {
        Perro perro = new Perro();
        perro.setNombre(dto.getNombre());
        perro.setRaza(dto.getRaza());
        perro.setEdad(dto.getEdad());
        perro.setTamano(dto.getTamano());
        perro.setSexo(dto.getSexo());
        perro.setDescripcion(dto.getDescripcion());
        perro.setFotoUrl(dto.getFotoUrl());
        return perro;
    }
}
package com.adopcion.repository;

import com.adopcion.model.Perro;
import com.adopcion.model.Tamano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerroRepository extends JpaRepository<Perro, Long> {
    
    // Spring Data JPA genera automáticamente estas consultas (Abstracción)
    List<Perro> findByNombreContainingIgnoreCase(String nombre);
    List<Perro> findByRazaContainingIgnoreCase(String raza);
    List<Perro> findByTamano(Tamano tamano);
}
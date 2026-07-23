package com.adopcion.config;

import com.adopcion.model.EstadoPerro;
import com.adopcion.model.Perro;
import com.adopcion.model.Sexo;
import com.adopcion.model.Tamano;
import com.adopcion.repository.PerroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PerroRepository perroRepository;

    public DataInitializer(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Carga automática de perros de prueba en la memoria RAM
        if (perroRepository.count() == 0) {
            Perro p1 = new Perro();
            p1.setNombre("Max");
            p1.setRaza("Mestizo");
            p1.setEdad(3);
            p1.setTamano(Tamano.MEDIANO);
            p1.setSexo(Sexo.MACHO);
            p1.setDescripcion("Max es un perrito muy juguetón y amoroso, rescatado de la calle hace 2 meses.");
            p1.setFotoUrl("https://images.unsplash.com/photo-1543466835-00a7907e9de1");
            p1.setEstado(EstadoPerro.DISPONIBLE);

            Perro p2 = new Perro();
            p2.setNombre("Luna");
            p2.setRaza("Labrador Retriever");
            p2.setEdad(2);
            p2.setTamano(Tamano.GRANDE);
            p2.setSexo(Sexo.HEMBRA);
            p2.setDescripcion("Luna es tranquila, ideal para familias con niños. Muy obediente.");
            p2.setFotoUrl("https://images.unsplash.com/photo-1552053831-71594a27632d");
            p2.setEstado(EstadoPerro.DISPONIBLE);

            Perro p3 = new Perro();
            p3.setNombre("Rocky");
            p3.setRaza("Bulldog");
            p3.setEdad(4);
            p3.setTamano(Tamano.MEDIANO);
            p3.setSexo(Sexo.MACHO);
            p3.setDescripcion("Rocky es de buen corazón, dormilón y muy amigable con otros animales.");
            p3.setFotoUrl("https://images.unsplash.com/photo-1583511655857-d19b40a7a54e");
            p3.setEstado(EstadoPerro.ADOPTADO);

            Perro p4 = new Perro();
            p4.setNombre("Kira");
            p4.setRaza("Poodle Mestizo");
            p4.setEdad(1);
            p4.setTamano(Tamano.PEQUENO);
            p4.setSexo(Sexo.HEMBRA);
            p4.setDescripcion("Kira es muy enérgica, necesita espacio para correr. Perfecta para deportistas.");
            p4.setFotoUrl("https://images.unsplash.com/photo-1517849845537-4d257902454a");
            p4.setEstado(EstadoPerro.DISPONIBLE);

            perroRepository.save(p1);
            perroRepository.save(p2);
            perroRepository.save(p3);
            perroRepository.save(p4);

            System.out.println("✅ ¡Base de datos en memoria inicializada con 4 perros de prueba!");
        }
    }
}
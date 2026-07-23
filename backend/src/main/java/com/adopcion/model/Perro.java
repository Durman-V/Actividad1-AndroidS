package com.adopcion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "perros")
public class Perro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String raza;

    @Column(nullable = false)
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Tamano tamano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Sexo sexo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPerro estado = EstadoPerro.DISPONIBLE;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters (Encapsulamiento)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public Tamano getTamano() { return tamano; }
    public void setTamano(Tamano tamano) { this.tamano = tamano; }
    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    public EstadoPerro getEstado() { return estado; }
    public void setEstado(EstadoPerro estado) { this.estado = estado; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
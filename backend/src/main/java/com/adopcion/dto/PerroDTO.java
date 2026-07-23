package com.adopcion.dto;

import com.adopcion.model.EstadoPerro;
import com.adopcion.model.Sexo;
import com.adopcion.model.Tamano;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO para transferir datos sin exponer la entidad directamente
public class PerroDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La raza es obligatoria")
    private String raza;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edad;

    @NotNull(message = "El tamaño es obligatorio")
    private Tamano tamano;

    @NotNull(message = "El sexo es obligatorio")
    private Sexo sexo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private String fotoUrl;
    private EstadoPerro estado;

    // Getters y Setters
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
}
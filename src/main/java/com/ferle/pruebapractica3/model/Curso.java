package com.ferle.pruebapractica3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Curso {
    @Id
    private int id;
    private String nombre;
    private String docente;
    private int horas;
    private double costo;
    private String modalidad;
}

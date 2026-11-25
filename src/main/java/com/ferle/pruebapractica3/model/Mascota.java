package com.ferle.pruebapractica3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Mascota {
    @Id
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private double peso;

}

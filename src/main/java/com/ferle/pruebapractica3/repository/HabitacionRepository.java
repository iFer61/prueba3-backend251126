package com.ferle.pruebapractica3.repository;

import com.ferle.pruebapractica3.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository <Habitacion, Integer>{
    List<Habitacion> findByDisponibleTrue();
}

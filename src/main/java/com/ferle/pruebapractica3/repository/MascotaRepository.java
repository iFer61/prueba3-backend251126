package com.ferle.pruebapractica3.repository;

import com.ferle.pruebapractica3.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository <Mascota, Integer> {
    List<Mascota> findByEspecieIgnoreCase(String especie);
}

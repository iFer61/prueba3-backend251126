package com.ferle.pruebapractica3.repository;

import com.ferle.pruebapractica3.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository <Curso, Integer> {
    @Query("SELECT COALESCE(AVG(c.costo), 0.0) FROM Curso c")
    Double obtenerPromedioCosto();

}

package com.ferle.pruebapractica3.service;

import com.ferle.pruebapractica3.model.Curso;
import com.ferle.pruebapractica3.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CursoService {
    private CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    //TRAER TODOS
    public List<Curso> traerTodos(){
        return cursoRepository.findAll();
    }

    //TRAER UNO
    public Optional<Curso> traerUno(int id){
        return cursoRepository.findById(id);
    }

    //GUARDAR
    public Curso  guardar (Curso curso){
        return cursoRepository.save(curso);
    }

    //EDITAR
    public Optional <Curso> editar(int id, Curso cursoActualizado) {
        return cursoRepository.findById(id)
                .map(cursoExiste -> {
                    cursoExiste.setModalidad(cursoActualizado.getModalidad());
                    cursoExiste.setDocente(cursoActualizado.getDocente());

                    return cursoRepository.save(cursoExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar(int id){
        if (cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Map<String, Double> calcularPromedioCosto() {
        Double promedio = cursoRepository.obtenerPromedioCosto();
        return Map.of("promedioCosto", promedio);
    }
}
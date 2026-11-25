package com.ferle.pruebapractica3.controller;

import com.ferle.pruebapractica3.model.Curso;
import com.ferle.pruebapractica3.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    private CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> verTodos(){
        return cursoService.traerTodos();
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Curso> traerUno(@PathVariable int id){
        return cursoService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<Curso> guardar(@RequestBody  Curso curso){
        Curso nuevoCurso = cursoService.guardar(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Curso> editar(@PathVariable int id, @RequestBody Curso curso){
        return cursoService.editar(id, curso)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity <Curso> eliminar (@PathVariable int id){
        if (cursoService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // CASO ESPECIAL: HACER PROMEDIO DE COSTOS
    @GetMapping("/promedio")
    public ResponseEntity<Map<String, Double>> obtenerPromedioCosto() {
        return ResponseEntity.ok(cursoService.calcularPromedioCosto());
    }

}

package com.ferle.pruebapractica3.controller;

import com.ferle.pruebapractica3.model.Habitacion;
import com.ferle.pruebapractica3.service.HabitacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
    private HabitacionService habitacionService;

    public HabitacionController( HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<Habitacion> verTodos(){
        return habitacionService.traerTodos();
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> traerUno(@PathVariable int id){
        return habitacionService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<Habitacion> guardar(@RequestBody  Habitacion habitacion){
        Habitacion nuevaHabitacion = habitacionService.guardar(habitacion);
        return new ResponseEntity<>(nuevaHabitacion, HttpStatus.CREATED);
    }

    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> editar(@PathVariable int id, @RequestBody Habitacion habitacion){
        return habitacionService.editar(id, habitacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Habitacion> eliminar (@PathVariable int id){
        if (habitacionService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    // CASO ESPECIAL: FILTRAR DISPONBLES
    @GetMapping("/disponibles")
    public ResponseEntity<Map<String, Object>> listarDisponibles() {
        return ResponseEntity.ok(habitacionService.obtenerHabitacionesDisponibles());
    }
}



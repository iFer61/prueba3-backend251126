package com.ferle.pruebapractica3.controller;

import com.ferle.pruebapractica3.model.Mascota;
import com.ferle.pruebapractica3.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {
    private MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService){
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> verTodos(){
        return mascotaService.traerTodos();
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> traerUno(@PathVariable int id){
        return mascotaService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<Mascota> guardar(@RequestBody  Mascota mascota){
        Mascota nuevaMascota = mascotaService.guardar(mascota);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }

    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> editar(@PathVariable int id, @RequestBody Mascota mascota){
        return mascotaService.editar(id, mascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Mascota> eliminar (@PathVariable int id){
        if (mascotaService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Mascota>> listarPorEspecie(@PathVariable String especie) {
        List<Mascota> mascotas = mascotaService.listarPorEspecie(especie);
        return ResponseEntity.ok(mascotas);
    }

}

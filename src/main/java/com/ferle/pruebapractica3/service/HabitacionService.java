package com.ferle.pruebapractica3.service;

import com.ferle.pruebapractica3.model.Habitacion;
import com.ferle.pruebapractica3.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HabitacionService {
    private HabitacionRepository habitacionRepository;

    public HabitacionService(HabitacionRepository habitacionRepository){
        this.habitacionRepository = habitacionRepository;
    }

    //TRAER TODOS
    public List<Habitacion> traerTodos(){
        return habitacionRepository.findAll();
    }

    //TRAER UNO
    public Optional<Habitacion> traerUno(int id){
        return habitacionRepository.findById(id);
    }

    //GUARDAR
    public Habitacion guardar (Habitacion habitacion){
        return habitacionRepository.save(habitacion);
    }

    //EDITAR
    public Optional <Habitacion> editar(int id, Habitacion habitacionActualizada){
        return habitacionRepository.findById(id)
                .map( habitacionExiste ->{
                    habitacionExiste.setCapacidad(habitacionActualizada.getCapacidad());
                    habitacionExiste.setPrecioNoche(habitacionActualizada.getPrecioNoche());

                    return habitacionRepository.save(habitacionExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar (int id){
        if ( habitacionRepository.existsById(id)){
            habitacionRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    // CASO ESPECIAL
    public Map<String, Object> obtenerHabitacionesDisponibles() {
        List<Habitacion> habitaciones = habitacionRepository.findByDisponibleTrue();
        return Map.of(
                "disponibles", true,
                "total", habitaciones.size(),
                "habitaciones", habitaciones
        );
    }


}

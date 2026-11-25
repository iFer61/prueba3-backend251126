package com.ferle.pruebapractica3.service;

import com.ferle.pruebapractica3.model.Mascota;
import com.ferle.pruebapractica3.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    private MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository){
        this.mascotaRepository = mascotaRepository;
    }

    //TRAER TODOS
    public List<Mascota> traerTodos(){
        return mascotaRepository.findAll();
    }

    //TRAER UNO
    public Optional<Mascota> traerUno(int id){
        return mascotaRepository.findById(id);
    }

    //GUARDAR
    public Mascota  guardar (Mascota mascota){
        return mascotaRepository.save(mascota);
    }

    //EDITAR
    public Optional <Mascota> editar(int id, Mascota mascotaActualizada){
        return mascotaRepository.findById(id)
                .map( mascotaExiste ->{
                    mascotaExiste.setPeso(mascotaActualizada.getPeso());
                    mascotaExiste.setEdad(mascotaActualizada.getEdad());

                    return mascotaRepository.save( mascotaExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar (int id){
        if ( mascotaRepository.existsById(id)){
            mascotaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    // CASO ESPECIAL: LISTAR POR ESPECIE
    public List<Mascota> listarPorEspecie(String especie) {
        return mascotaRepository.findByEspecieIgnoreCase(especie);
    }
}

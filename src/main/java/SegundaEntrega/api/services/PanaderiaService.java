package SegundaEntrega.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.repository.PanaderiaRepository;

@Service
public class PanaderiaService {
    @Autowired
    private PanaderiaRepository panaderiaRepository;

    public List<Panaderia> getAllPanaderias(){
        return panaderiaRepository.findAll();
    }
    // trae una panaderia por id
    public Optional<Panaderia> getPanaderiaById(Long id){
        return panaderiaRepository.findById(id);
    }
    // guarda una panaderia o sucursal
    public Panaderia savePanaderia(Panaderia panaderia){
        return panaderiaRepository.save(panaderia);
    }
    // borra una panaderia con validacion
    public void deletePanaderia(Long id){
        if (panaderiaRepository.existsById(id)){
            panaderiaRepository.deleteById(id);
        } else {
            System.out.println("La panaderia no existe");
        }
    }
    public Panaderia updatePanaderia(Long id, PanaderiaDTO panaderiaDTO) {
        Optional<Panaderia> optionalPanaderia = panaderiaRepository.findById(id);

        if (optionalPanaderia.isPresent()) {
            Panaderia existingPanaderia = optionalPanaderia.get();

            existingPanaderia.setNombre(panaderiaDTO.getNombre());
            existingPanaderia.setDireccion(panaderiaDTO.getDireccion());
            existingPanaderia.setTelefono(panaderiaDTO.getTelefono());

            return panaderiaRepository.save(existingPanaderia);
        } else {
            throw new RuntimeException("Panadería no encontrada con ID: " + id);
        }
    }
}

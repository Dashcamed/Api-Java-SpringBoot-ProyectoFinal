package SegundaEntrega.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.mapper.PanaderiaMapper;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.repository.PanaderiaRepository;

@Service
public class PanaderiaService {
    @Autowired
    private final PanaderiaRepository panaderiaRepository;
    @Autowired final PanaderiaMapper panaderiaMapper;

    public PanaderiaService(PanaderiaMapper panaderiaMapper, PanaderiaRepository panaderiaRepository){
        this.panaderiaMapper = panaderiaMapper;
        this.panaderiaRepository = panaderiaRepository;
    }

    public List<PanaderiaDTO> getAllPanaderias(){
        return panaderiaRepository.findAll().stream()
                .map(panaderiaMapper::toDTOPanaderia)
                .collect(Collectors.toList());
    }

    public Optional<PanaderiaDTO> getPanaderiaById(Long id){
        return panaderiaRepository.findById(id).map(panaderiaMapper::toDTOPanaderia);
    }

    public PanaderiaDTO savePanaderia(PanaderiaDTO panaderiaDTO){
        Panaderia panaderia = panaderiaMapper.toEntity(panaderiaDTO);
        Panaderia savedPanaderia = panaderiaRepository.save(panaderia);
        return panaderiaMapper.toDTOPanaderia(savedPanaderia);
    }

    public void deletePanaderia(Long id){
        if (panaderiaRepository.existsById(id)){
            panaderiaRepository.deleteById(id);
        } else {
            System.out.println("La panaderia no existe");
        }
    }

    public PanaderiaDTO updatePanaderia(Long id, PanaderiaDTO panaderiaDTO) {
        return panaderiaRepository.findById(id)
            .map(panaderia -> {
                panaderia.setNombre(panaderiaDTO.getNombre());
                panaderia.setDireccion(panaderiaDTO.getDireccion());
                panaderia.setTelefono(panaderiaDTO.getTelefono());
                return panaderiaMapper.toDTOPanaderia(panaderiaRepository.save(panaderia));
            })
            .orElse(null);

    }
}

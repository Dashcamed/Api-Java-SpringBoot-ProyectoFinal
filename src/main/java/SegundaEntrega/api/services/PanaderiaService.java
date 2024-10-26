package SegundaEntrega.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SegundaEntrega.api.DTO.PanaderiaCreateDTO;
import SegundaEntrega.api.DTO.PanaderiaDTO;
import SegundaEntrega.api.mapper.PanaderiaMapper;
import SegundaEntrega.api.model.Panaderia;
import SegundaEntrega.api.repository.PanaderiaRepository;

@Service
public class PanaderiaService {
    @Autowired
    private final PanaderiaRepository panaderiaRepository;
    @Autowired
    private final PanaderiaMapper panaderiaMapper;

    public PanaderiaService(PanaderiaMapper panaderiaMapper, PanaderiaRepository panaderiaRepository) {
        this.panaderiaMapper = panaderiaMapper;
        this.panaderiaRepository = panaderiaRepository;
    }

    public List<PanaderiaDTO> getAllPanaderias(boolean includeRelations) {
        
        return panaderiaRepository.findAll().stream()
                .map(panaderia -> panaderiaMapper.toDTOPanaderia(panaderia, includeRelations))
                .collect(Collectors.toList());
    }

    public Optional<PanaderiaDTO> getPanaderiaById(Long id, boolean includeRelations) {
        return panaderiaRepository.findById(id)
                .map(panaderia -> panaderiaMapper.toDTOPanaderia(panaderia, includeRelations));
    }

    public PanaderiaDTO savePanaderia(PanaderiaCreateDTO panaderiaCreateDTO) {
    Panaderia panaderia = panaderiaMapper.toEntity(panaderiaCreateDTO);
    Panaderia savedPanaderia = panaderiaRepository.save(panaderia);
    return panaderiaMapper.toDTOPanaderia(savedPanaderia, false);
}

public PanaderiaDTO updatePanaderia(Long id, PanaderiaCreateDTO panaderiaCreateDTO) {
    return panaderiaRepository.findById(id)
        .map(panaderia -> {
            panaderia.setNombre(panaderiaCreateDTO.getNombre());
            panaderia.setDireccion(panaderiaCreateDTO.getDireccion());
            panaderia.setTelefono(panaderiaCreateDTO.getTelefono());
            return panaderiaMapper.toDTOPanaderia(panaderiaRepository.save(panaderia), false);
        })
        .orElse(null);
}

    public void deletePanaderia(Long id) {
        if (panaderiaRepository.existsById(id)) {
            panaderiaRepository.deleteById(id);
        } else {
            System.out.println("La panadería no existe");
        }
    }
}

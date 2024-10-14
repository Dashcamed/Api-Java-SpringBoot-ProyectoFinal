package SegundaEntrega.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SegundaEntrega.api.model.Panaderia;

@Repository
public interface PanaderiaRepository extends JpaRepository <Panaderia, Long> {
    Optional<Panaderia> findById(Long id);
}

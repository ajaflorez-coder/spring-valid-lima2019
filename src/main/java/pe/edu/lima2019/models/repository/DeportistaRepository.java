package pe.edu.lima2019.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.lima2019.models.entity.Deportista;

@Repository
public interface DeportistaRepository extends JpaRepository<Deportista, Integer>{
	Optional<Deportista> findByNumDocumento(String numDocumento);
}

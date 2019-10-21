package pe.edu.lima2019.service;

import java.util.Optional;

import pe.edu.lima2019.models.entity.Deportista;

public interface DeportistaService extends CrudService<Deportista, Integer>{
	Optional<Deportista> findByNumDocumento( String numDocumento ) throws Exception;
}

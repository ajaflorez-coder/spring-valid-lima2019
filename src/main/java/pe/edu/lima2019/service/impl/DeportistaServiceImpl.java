package pe.edu.lima2019.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.lima2019.models.entity.Deportista;
import pe.edu.lima2019.models.repository.DeportistaRepository;
import pe.edu.lima2019.service.DeportistaService;

@Service
public class DeportistaServiceImpl implements DeportistaService{

	@Autowired
	private DeportistaRepository deportistaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Deportista> findAll() throws Exception {
		return deportistaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Deportista> findById(Integer id) throws Exception {
		return deportistaRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Deportista> findByNumDocumento(String numDocumento) throws Exception {
		return deportistaRepository.findByNumDocumento(numDocumento);
	}

	@Override
	@Transactional
	public Deportista save(Deportista entity) throws Exception {
		return deportistaRepository.save(entity);
	}

	@Override
	@Transactional
	public Deportista update(Deportista entity) throws Exception {
		return deportistaRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		deportistaRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public void deleteAll() throws Exception {
		deportistaRepository.deleteAll();
		
	}

	

}

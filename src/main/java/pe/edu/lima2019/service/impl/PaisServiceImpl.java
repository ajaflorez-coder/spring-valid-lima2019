package pe.edu.lima2019.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.lima2019.models.entity.Pais;
import pe.edu.lima2019.models.repository.PaisRepository;
import pe.edu.lima2019.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService{

	@Autowired
	private PaisRepository paisRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAll() throws Exception {
		return paisRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pais> findById(Integer id) throws Exception {
		return paisRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Pais save(Pais entity) throws Exception {
		return paisRepository.save(entity);
	}

	@Override
	@Transactional
	public Pais update(Pais entity) throws Exception {
		return paisRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		paisRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public void deleteAll() throws Exception {
		paisRepository.deleteAll();
		
	}

}

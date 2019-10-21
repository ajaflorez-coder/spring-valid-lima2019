package pe.edu.lima2019.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.lima2019.models.entity.Medalla;
import pe.edu.lima2019.models.repository.MedallaRepository;
import pe.edu.lima2019.service.MedallaService;


@Service
public class MedallaServiceImpl implements MedallaService{

	@Autowired
	private MedallaRepository medallaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Medalla> findAll() throws Exception {
		return medallaRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Medalla> fetchAllOrderByPais() throws Exception {
		return medallaRepository.fetchAllOrderPais();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Medalla> findById(Integer id) throws Exception {
		return medallaRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Medalla save(Medalla entity) throws Exception {
		return medallaRepository.save(entity);
	}

	@Override
	@Transactional
	public Medalla update(Medalla entity) throws Exception {
		return medallaRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		medallaRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public void deleteAll() throws Exception {
		medallaRepository.deleteAll();
		
	}

	

}

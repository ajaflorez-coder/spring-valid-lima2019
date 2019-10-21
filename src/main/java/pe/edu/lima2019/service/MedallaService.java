package pe.edu.lima2019.service;

import java.util.List;

import pe.edu.lima2019.models.entity.Medalla;

public interface MedallaService extends CrudService<Medalla, Integer>{

	List<Medalla> fetchAllOrderByPais() throws Exception;
}

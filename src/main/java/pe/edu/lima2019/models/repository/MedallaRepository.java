package pe.edu.lima2019.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.lima2019.models.entity.Medalla;

@Repository
public interface MedallaRepository extends JpaRepository<Medalla, Integer>{
	
	@Query("SELECT m FROM Medalla m ORDER BY m.deportista.pais.id")
	List<Medalla> fetchAllOrderPais();
}

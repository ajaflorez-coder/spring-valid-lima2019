package pe.edu.lima2019.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.lima2019.models.entity.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer>{

}

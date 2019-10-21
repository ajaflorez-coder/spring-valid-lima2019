package pe.edu.lima2019.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.lima2019.models.entity.Pais;
import pe.edu.lima2019.service.PaisService;

@RestController
@RequestMapping(path = "/api/paises")
public class PaisRestController {

	@Autowired
	private PaisService paisService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pais>> getAll() {
		try {
			List<Pais> paises = paisService.findAll();
			return new ResponseEntity<List<Pais>>(paises, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Pais>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

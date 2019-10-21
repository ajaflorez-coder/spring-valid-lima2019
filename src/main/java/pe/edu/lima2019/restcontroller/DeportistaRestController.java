package pe.edu.lima2019.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.lima2019.models.entity.Deportista;
import pe.edu.lima2019.service.DeportistaService;
import pe.edu.lima2019.validators.DeportistaValidator;
import pe.edu.lima2019.validators.ErrorMessageBuilder;
import pe.edu.lima2019.validators.ErrorMessageRest;

@RestController
@RequestMapping(path = "/api/deportistas")
public class DeportistaRestController {

	@Autowired
	private DeportistaService deportistaService;
	
	@Autowired
	private DeportistaValidator deportistaValidator;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Deportista>> getAll() {
		try {
			List<Deportista> deportistas = deportistaService.findAll();
			return new ResponseEntity<List<Deportista>>(deportistas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Deportista>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		try {
			Optional<Deportista> deportista = deportistaService.findById(id);
			if(deportista.isPresent()) {
				return new ResponseEntity<Deportista>(deportista.get(), HttpStatus.OK);
			} else {
				ErrorMessageRest errorMessageRest = new ErrorMessageRest(400, "Bad Request", "Error en los datos enviados: ");
				errorMessageRest.addError("El Id enviado no existe");
				return ResponseEntity.badRequest().body( errorMessageRest );
			}
			
		} catch (Exception e) {
			return new ResponseEntity<List<Deportista>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postSave(@RequestBody @Valid Deportista deportista, Errors errors) {
		deportistaValidator.validate(deportista, errors);
		
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body( ErrorMessageBuilder.build(errors) );
		} else {
			try {
				Deportista nuevo = deportistaService.save(deportista);
				return new ResponseEntity<Deportista>(nuevo, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<Deportista>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
	}
	
}







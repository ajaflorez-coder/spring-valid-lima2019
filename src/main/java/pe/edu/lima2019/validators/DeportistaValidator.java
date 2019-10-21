package pe.edu.lima2019.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pe.edu.lima2019.models.entity.Deportista;
import pe.edu.lima2019.service.DeportistaService;

@Component
public class DeportistaValidator implements Validator{

	@Autowired
	private DeportistaService deportistaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Deportista.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Deportista deportista = (Deportista)target;
		try {
			Optional<Deportista> deportistaOptional = deportistaService.findByNumDocumento(deportista.getNumDocumento());
			if( deportistaOptional.isPresent() ) {
				errors.rejectValue("numDocumento", "numDocumento.exists", 
						new Object[]{deportista.getNumDocumento()}, 
						"El número de documentos " + deportista.getNumDocumento() + " ya existe");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}

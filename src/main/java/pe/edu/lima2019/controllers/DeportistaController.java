package pe.edu.lima2019.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.lima2019.models.entity.Deportista;
import pe.edu.lima2019.models.entity.Pais;
import pe.edu.lima2019.service.DeportistaService;
import pe.edu.lima2019.service.PaisService;
import pe.edu.lima2019.validators.DeportistaValidator;

@Controller
@RequestMapping("/deportistas")
@SessionAttributes({"deportista", "paises"})
public class DeportistaController {

	@Autowired
	private DeportistaService deportistaService;
	
	@Autowired
	private PaisService paisService;
	//private MedallaService medallaService;
	
	@Autowired
	private DeportistaValidator deportistaValidator;
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Deportista> deportistas = deportistaService.findAll();
			model.addAttribute("deportistas", deportistas);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/deportistas/inicio";
	}
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Deportista deportista = new Deportista();
			model.addAttribute("deportista", deportista);

			List<Pais> paises = paisService.findAll();
			model.addAttribute("paises", paises);			
		} catch (Exception e) {
			model.addAttribute("alertClass", "danger");
			model.addAttribute("message", e.getMessage());
		}
		
		return "/deportistas/nuevo"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("deportista") @Valid Deportista deportista, BindingResult bindingResult, Model model, SessionStatus status) {
		deportistaValidator.validate(deportista, bindingResult);
		if (bindingResult.hasErrors()) {
            return "/deportistas/nuevo"; 
        } else {
			try {
				Optional<Deportista> doble = deportistaService.findByNumDocumento(deportista.getNumDocumento());
				if( doble.isPresent() ) {
					model.addAttribute("alertClass", "danger");
					model.addAttribute("message", "Ya existe un deportista con el mismo Número de Documento de identidad");
					return "/deportistas/edit"; 
				}				
				deportistaService.save(deportista);
				status.setComplete();
				model.addAttribute("alertClass", "success");
				model.addAttribute("message", "Se registro de forma satisfactoria el Deportista");
			} catch (Exception e) {
				model.addAttribute("alertClass", "danger");
				model.addAttribute("message", e.getMessage());
				return "/deportistas/nuevo"; 
			} 
			return "redirect:/deportistas";
        }
	}
}

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.lima2019.models.entity.Pais;
import pe.edu.lima2019.service.PaisService;

@Controller
@RequestMapping("/paises")
@SessionAttributes({"pais"})
public class PaisController {

	@Autowired
	private PaisService paisService;
	
	@GetMapping
	public String getPaises(Model model) {
		try {
			List<Pais> paises = paisService.findAll();
			model.addAttribute("paises", paises);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/paises/init";
	}
	@GetMapping("/edit/{id}")
	public String getPaisById(@PathVariable("id") int id, Model model) {
		
		try {
			Optional<Pais> paisOptional = paisService.findById(id);
			if (paisOptional.isPresent()) {
				model.addAttribute("pais", paisOptional.get());
			} else {
				model.addAttribute("alertClass", "danger");
				model.addAttribute("message", "El id de Pais no existe");				
				return "redirect:/paises";
			}
		} catch (Exception e) {
			model.addAttribute("alertClass", "danger");
			model.addAttribute("message", e.getMessage());
		}
		
		return "/paises/edit";
	}
	
	@GetMapping("/new")
	public String getPaisesNew(Model model) {
		try {
			Pais pais = new Pais();
			model.addAttribute("pais", pais);
		} catch (Exception e) {
			model.addAttribute("alertClass", "danger");
			model.addAttribute("message", e.getMessage());
		}
		
		return "/paises/new"; 
	}
	
	@PostMapping("/save")
	public String postPaisSave(@ModelAttribute("pais") @Valid Pais pais, BindingResult bindingResult, Model model, SessionStatus status) {
		if (bindingResult.hasErrors()) {
            return "/deportistas/nuevo"; 
        } else {
			try {
				paisService.save(pais);
				status.setComplete();
				model.addAttribute("alertClass", "success");
				model.addAttribute("message", "Se registro de forma satisfactoria el pais");
			} catch (Exception e) {
				model.addAttribute("alertClass", "danger");
				model.addAttribute("message", e.getMessage());
				return "/pais/new"; 
			} 
			return "redirect:/paises";
        }
	}
}

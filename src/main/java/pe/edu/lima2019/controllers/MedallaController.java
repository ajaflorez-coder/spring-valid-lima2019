package pe.edu.lima2019.controllers;

import java.util.List;

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
import pe.edu.lima2019.models.entity.Medalla;
import pe.edu.lima2019.service.DeportistaService;
import pe.edu.lima2019.service.MedallaService;

@Controller
@RequestMapping("/medallas")
@SessionAttributes({"medalla", "deportistas"})
public class MedallaController {
	@Autowired
	private DeportistaService deportistaService;
	
	@Autowired
	private MedallaService medallaService;

	@GetMapping
	public String listado(Model model) {
		try {
			List<Medalla> medallas = medallaService.fetchAllOrderByPais();
			model.addAttribute("medallas", medallas);
		} catch (Exception e) {
			model.addAttribute("alertClass", "danger");
			model.addAttribute("message", e.getMessage());
		}
		return "/medallas/inicio";
	}
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Medalla medalla = new Medalla();
			model.addAttribute("medalla", medalla);

			List<Deportista> deportistas = deportistaService.findAll();
			model.addAttribute("deportistas", deportistas);			
		} catch (Exception e) {
			model.addAttribute("alertClass", "danger");
			model.addAttribute("message", e.getMessage());
		}
		return "/medallas/nuevo"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("medalla") @Valid Medalla medalla, BindingResult bindingResult, Model model, SessionStatus status) {
		if (bindingResult.hasErrors()) {
            return "/medallas/nuevo"; 
        } else {
			try {
				medallaService.save(medalla);
				status.setComplete();
				model.addAttribute("alertClass", "success");
				model.addAttribute("message", "Se registro de forma satisfactoria la Medalla");
			} catch (Exception e) {
				model.addAttribute("alertClass", "danger");
				model.addAttribute("message", e.getMessage());
				return "/medallas/nuevo";
			} 
			return "redirect:/medallas";
        }
	}
}

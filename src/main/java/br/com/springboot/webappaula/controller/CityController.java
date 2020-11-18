package br.com.springboot.webappaula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springboot.webappaula.model.City;
import br.com.springboot.webappaula.repository.CityRepository;

//RestController
@Controller
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityRepository cityRepository;
	
	public CityController(CityRepository cityRepository) {
		this.cityRepository = cityRepository; 
	}
	
//	//Para endpoint API -> Usar a anotation RestContorller
//    @GetMapping("/list")
//    public List<City> list(){
//        return this.cityRepository.findAll();
//    }
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("cities", this.cityRepository.findAll());
		return "cities/index";
	}
	
	@GetMapping("/new")
	public String newCity(Model model) {
		model.addAttribute("city", new City());
		return "cities/new";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute("city") City city, Model model) {
		this.cityRepository.save(city);
		return "redirect:/cities";
	}
	
	@GetMapping("/{id}")
	public String editCity(@PathVariable("id") Long id, Model model) {
		model.addAttribute("city", this.cityRepository.getOne(id));
		
		return "cities/edit";
	}
	
	@PostMapping("{id}")
	public String update(@PathVariable("id") Long id, @ModelAttribute("city") City city, Model model) {
		City findCity = this.cityRepository.getOne(id);
		
		if (findCity != null) {
			findCity.setId(city.getId());
			findCity.setName(city.getName());
			
			this.cityRepository.save(findCity);
			
			return "redirect:/cities";
		}
		
		return "redirect:/cities";
	}
	
	@DeleteMapping("{id}")
	public String remove(@PathVariable("id") Long id, Model model) {
		City city = this.cityRepository.getOne(id);
		
		if (city != null) {
			this.cityRepository.delete(city);
			return "redirect:/cities";			
		}

		return "redirect:/cities";
	}

}
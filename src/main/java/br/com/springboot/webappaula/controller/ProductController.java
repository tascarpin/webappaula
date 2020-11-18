package br.com.springboot.webappaula.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.webappaula.model.Product;
import br.com.springboot.webappaula.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;	
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@ResponseBody
	public List<Product> findAll(){
		return this.productService.findAll();
	}
	
	@GetMapping(value="/{id}")
	@ResponseBody
	public Optional<Product> find(@PathVariable(value = "id") Integer id) {
		return this.productService.find(id);
	}
	
	@PostMapping
	@ResponseBody
	public Product create(@RequestBody Product product) {
		return this.productService.create(product);
	}
	
	@PutMapping(value="/{id}")
	@ResponseBody	
	public Product update(@PathVariable(value="id") Integer id, @RequestBody Product product) {
		return this.productService.update(id, product);
	}
	
	@DeleteMapping(value="/{id}")	
	public void delete(@PathVariable(value="id") Integer id, HttpServletResponse response) {
		this.productService.delete(id);
		//Será gerada uma resposta 204 informando que não há nada para ser retornado ao cliente.
		response.setStatus(HttpServletResponse.SC_NO_CONTENT); 
	}
}
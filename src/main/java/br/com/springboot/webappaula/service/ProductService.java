package br.com.springboot.webappaula.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.webappaula.model.Product;
import br.com.springboot.webappaula.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;	

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	public Optional<Product> find(Integer id) {
		return this.productRepository.findById(id);
	}

	public Product create(Product product) {
		this.productRepository.save(product);
		return product;
	}

	public Product update(Integer id, Product product) {
		Product productExists = this.productRepository.getOne(id);
		if(productExists != null) {
			product.setId(productExists.getId());
			this.productRepository.save(product);
			return product;
		}
		return null;
	}

	public void delete(Integer id) {		
		Product product = this.productRepository.getOne(id);
		if(product != null) 
			this.productRepository.delete(product);
	}
}
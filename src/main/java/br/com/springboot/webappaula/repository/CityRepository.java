package br.com.springboot.webappaula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.webappaula.model.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.springboot.webappaula.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pinhe
 */
@RestController
@RequestMapping
public class HelloWorldController {
     
	//Trabalhando sem passar Model no parametro
	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/hello");		
		//Adiionando dados a view
		mv.addObject("name", "Tassio Pinheiro");
		return mv;
	} 
	
    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello Tassio Pinheiro";
    }
    
    @GetMapping("/sayHello/Tassio")
    public String subPath(){
        return "Hello Tassio Carneiro Pinheiro";
    }
    
    //Query String dentro da rota. Ex: http://localhost:8080/sayHello/param?name=Tassio
    @GetMapping("/param2")
    public String subPath2(@RequestParam("name") String name){
        return "This is one subPath of endpoint /param2 " + name;
    }
    
    @GetMapping("/param3")
    public String subPath3(final WebRequest webResquest){
        String name = webResquest.getParameter("name");
        if(name == null){
            return "No query params3.";
        }
        return "This is one subPath of endpoint /param3 " + name;
    }
    
    @GetMapping("/param4")
    @ResponseBody
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public String subPath4(@RequestParam(value="name", required=false) String name){
        return "This is one subPath of endpoint /param4 " + name;
    }
    
    @PostMapping("/post")
    public String sayHelloPost(@RequestBody Map<String, Object> payload){
        return payload.get("name").toString();
    }
    
    @GetMapping("/Tassio/{name}")
    public String dynamicSubPath(@PathVariable("name") String name){
        return "Hello Tassio " + name + " this is my route dynamic";
    }
    
}

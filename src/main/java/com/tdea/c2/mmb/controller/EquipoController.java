package com.tdea.c2.mmb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Equipo;
import com.tdea.c2.mmb.repository.IEquipoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/api")
public class EquipoController {
	
	@Autowired
	private IEquipoRepository equipoRepository;
	
	@GetMapping("/equipos")
	public List<Equipo> getAllEquipos(){
		
		return equipoRepository.findAll();
	}
	
	@GetMapping("/equipos/{id}")
	public Optional<Equipo> getEquipoById(@PathVariable("id") Long id){
		
		return equipoRepository.findById(id);
	}
	
	@PostMapping("/equipos")
	public Equipo createEquipo(@RequestBody Equipo equipo) {
		
		return equipoRepository.save(equipo);
	}

}

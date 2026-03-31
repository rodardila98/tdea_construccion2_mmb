package com.tdea.c2.mmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Equipo;
import com.tdea.c2.mmb.repository.IEquipoRepository;

@RestController
@RequestMapping("/api")
public class EquipoController {
	
	@Autowired
	private IEquipoRepository equipoRepository;
	
	@GetMapping("/equipos")
	public List<Equipo> getAllEquipos(){
		
		return equipoRepository.findAll();
	}

}

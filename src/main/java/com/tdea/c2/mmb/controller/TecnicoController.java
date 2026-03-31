package com.tdea.c2.mmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.Tecnico;
import com.tdea.c2.mmb.repository.ITecnicoRepository;

@RestController
@RequestMapping("/api")
public class TecnicoController {
	
	@Autowired
	private ITecnicoRepository tecnicoRepository;
	
	@GetMapping("/tecnicos")
	public List<Tecnico> getAllTecnicos(){
		
		return tecnicoRepository.findAll();
	}

}

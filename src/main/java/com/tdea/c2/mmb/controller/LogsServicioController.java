package com.tdea.c2.mmb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.LogsServicios;
import com.tdea.c2.mmb.repository.ILogsServicioRepository;

@RestController
@RequestMapping("/api")
public class LogsServicioController {
	
	@Autowired
	private ILogsServicioRepository logsServicioRepository;
	
	@GetMapping("/logsservicios")
	public List<LogsServicios> getAllLogsServicios(){
		
		return logsServicioRepository.findAll();
	}

}

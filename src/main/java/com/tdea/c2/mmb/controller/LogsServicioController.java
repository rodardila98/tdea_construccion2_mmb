package com.tdea.c2.mmb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdea.c2.mmb.modelo.LogsServicios;
import com.tdea.c2.mmb.service.ILogsServicioService;

@RestController
@RequestMapping("/api")
public class LogsServicioController {
	
	@Autowired
	private ILogsServicioService logsServicioService;
	
	@GetMapping("/logsservicios")
	public ResponseEntity<List<LogsServicios>> getAllLogsServicios(){
		List<LogsServicios> logs = logsServicioService.getAllLogsServicios();
		if (logs == null || logs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(logs);
	}

	@GetMapping("/logsservicios/{id}")
	public ResponseEntity<LogsServicios> getLogsById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(logsServicioService.getLogsById(id));
	}

	@PostMapping("/logsservicios")
	public ResponseEntity<?> createLogs(@RequestBody LogsServicios logs) {
		try {
			LogsServicios saved = logsServicioService.createLogs(logs);
			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/logsservicios/{id}")
	public ResponseEntity<LogsServicios> updateLogs(@PathVariable("id") Integer id, @RequestBody LogsServicios cambios) {
		try {
			LogsServicios updated = logsServicioService.updateLogs(id, cambios);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/logsservicios/{id}")
	public ResponseEntity<LogsServicios> deleteLogs(@PathVariable("id") Integer id){
		logsServicioService.deleteLogs(id);
		return ResponseEntity.noContent().build();
	}
}
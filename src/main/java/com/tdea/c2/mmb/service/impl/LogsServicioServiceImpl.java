package com.tdea.c2.mmb.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdea.c2.mmb.modelo.LogsServicios;
import com.tdea.c2.mmb.repository.ILogsServicioRepository;
import com.tdea.c2.mmb.service.ILogsServicioService;

@Service
public class LogsServicioServiceImpl implements ILogsServicioService {

	@Autowired
	private ILogsServicioRepository logsServicioRepository;

	@Override
	public List<LogsServicios> getAllLogsServicios() {
		return logsServicioRepository.findAll();
	}

	@Override
	public Optional<LogsServicios> getLogsById(Integer id) {
		return logsServicioRepository.findById(id);
	}

	@Override
	public LogsServicios createLogs(LogsServicios logs) {
		validarLogs(logs);
		logs.setFechaModificacion(LocalDateTime.now());
		return logsServicioRepository.save(logs);
	}

	@Override
	public LogsServicios updateLogs(Integer id, LogsServicios cambios) {
		LogsServicios existente = logsServicioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("LogsServicios no encontrado con id: " + id));
		
		existente.setServicio(cambios.getServicio());
		existente.setFechaServicio(cambios.getFechaServicio());
		existente.setHoraServicio(cambios.getHoraServicio());
		existente.setTipoServicio(cambios.getTipoServicio());
		existente.setEstadoServicio(cambios.getEstadoServicio());
		existente.setFechaModificacion(LocalDateTime.now());
		
		return logsServicioRepository.save(existente);
	}

	@Override
	public void deleteLogs(Integer id) {
		logsServicioRepository.deleteById(id);
	}

	// Método privado para validaciones
	private void validarLogs(LogsServicios logs) {
		if (logs == null) {
			throw new IllegalArgumentException("LogsServicios no puede ser nulo");
		}
		if (logs.getServicio() == null) {
			throw new IllegalArgumentException("Campo 'servicio' es requerido");
		}
	}
}
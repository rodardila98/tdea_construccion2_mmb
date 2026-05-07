package com.tdea.c2.mmb.service;

import java.util.List;
import java.util.Optional;

import com.tdea.c2.mmb.modelo.LogsServicios;

public interface ILogsServicioService {

	List<LogsServicios> getAllLogsServicios();
	Optional<LogsServicios> getLogsById(Integer id);
	LogsServicios createLogs(LogsServicios logs);
	LogsServicios updateLogs(Integer id, LogsServicios logs);
	void deleteLogs(Integer id);

}
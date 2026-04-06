package com.tdea.c2.mmb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdea.c2.mmb.modelo.LogsServicios;

@Repository
public interface ILogsServicioRepository extends JpaRepository<LogsServicios, Integer> {
	
	// Obtener historial de un servicio ordenado por fecha
	@Query("SELECT l FROM LogsServicios l WHERE l.servicio.idServicio = :idServicio ORDER BY l.fechaModificacion DESC")
	List<LogsServicios> findByServicioId(@Param("idServicio") int idServicio);
	
}

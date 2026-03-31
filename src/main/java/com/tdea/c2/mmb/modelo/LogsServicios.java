package com.tdea.c2.mmb.modelo;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Logs")

public class LogsServicios extends Servicio{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idHistorial;
	private Date fechaModificacion;

	// Constructor con sólo los atributos de la clase padre
	public LogsServicios(int idServicio, Date fechaServicio, String horaServicio, String tipoServicio,
			String estadoServicio) {
		super(idServicio, fechaServicio, horaServicio, tipoServicio, estadoServicio);
		// TODO Auto-generated constructor stub
	}

	// Constructor con todos los atributos, incluyendo los de la clase padre
	public LogsServicios(int idServicio, Date fechaServicio, String horaServicio, String tipoServicio,
			String estadoServicio, int idHistorial, Date fechaModificacion) {
		super(idServicio, fechaServicio, horaServicio, tipoServicio, estadoServicio);
		this.idHistorial = idHistorial;
		this.fechaModificacion = fechaModificacion;
	}
}

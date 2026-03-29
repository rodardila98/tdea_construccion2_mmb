package com.tdea.c2.modelo;

import java.util.Date;

public class LogsServicios extends Servicio{
	
	public LogsServicios(int idServicio, Date fechaServicio, String horaServicio, String tipoServicio,
			String estadoServicio) {
		super(idServicio, fechaServicio, horaServicio, tipoServicio, estadoServicio);
		// TODO Auto-generated constructor stub
	}
	private int idHistorial;
	private Date fechaModificacion;

}

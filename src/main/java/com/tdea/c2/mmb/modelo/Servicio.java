package com.tdea.c2.mmb.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio")
public class Servicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServicio;
	
	private LocalDate fechaServicio;
	private String horaServicio;
	private String tipoServicio;
	private String estadoServicio;
	
	// Constructor vacío (requerido por JPA)
	public Servicio() {
	}
	
	public Servicio(Integer idServicio, LocalDate fechaServicio, String horaServicio, String tipoServicio,
			String estadoServicio) {
		this.idServicio = idServicio;
		this.fechaServicio = fechaServicio;
		this.horaServicio = horaServicio;
		this.tipoServicio = tipoServicio;
		this.estadoServicio = estadoServicio;
	}
	
	// Getters y Setters
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}
	public LocalDate getFechaServicio() {
		return fechaServicio;
	}
	public void setFechaServicio(LocalDate fechaServicio) {
		this.fechaServicio = fechaServicio;
	}
	public String getHoraServicio() {
		return horaServicio;
	}
	public void setHoraServicio(String horaServicio) {
		this.horaServicio = horaServicio;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public String getEstadoServicio() {
		return estadoServicio;
	}
	public void setEstadoServicio(String estadoServicio) {
		this.estadoServicio = estadoServicio;
	}
	
}
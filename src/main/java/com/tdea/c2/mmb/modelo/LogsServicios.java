package com.tdea.c2.mmb.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;

@Entity
@Table(name = "logs_servicios")
public class LogsServicios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHistorial;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "fk_servicio"))
	private Servicio servicio;
	
	// Estado completo del servicio en ese momento
	private LocalDate fechaServicio;
	private String horaServicio;
	private String tipoServicio;
	private String estadoServicio;
	
	// Metadatos
	@Column(nullable = false)
	private LocalDateTime fechaModificacion;
	
	// Constructor vacío (requerido por JPA)
	public LogsServicios() {
	}
	
	// Constructor que recibe el servicio completo
	public LogsServicios(Servicio servicio) {
		this.servicio = servicio;
		this.fechaServicio = servicio.getFechaServicio();
		this.horaServicio = servicio.getHoraServicio();
		this.tipoServicio = servicio.getTipoServicio();
		this.estadoServicio = servicio.getEstadoServicio();
		this.fechaModificacion = LocalDateTime.now();
	}
	
	// Getters y Setters
	public Integer getIdHistorial() {
		return idHistorial;
	}
	public void setIdHistorial(Integer idHistorial) {
		this.idHistorial = idHistorial;
	}
	
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
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
	
	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
}
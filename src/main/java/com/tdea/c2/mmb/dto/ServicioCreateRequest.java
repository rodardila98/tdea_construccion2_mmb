package com.tdea.c2.mmb.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ServicioCreateRequest {
	
	@NotNull(message = "Campo 'fechaServicio' es requerido")
	private LocalDate fechaServicio;
	
	private String horaServicio;
	
	@NotBlank(message = "Campo 'tipoServicio' es requerido")
	private String tipoServicio;
	
	@NotBlank(message = "Campo 'estadoServicio' es requerido")
	private String estadoServicio;
	
	@NotNull(message = "Campo 'clienteId' es requerido")
	private Integer clienteId;
	
	@NotNull(message = "Campo 'tecnicoId' es requerido")
	private Integer tecnicoId;
	
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
	
	public Integer getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	
	public Integer getTecnicoId() {
		return tecnicoId;
	}
	
	public void setTecnicoId(Integer tecnicoId) {
		this.tecnicoId = tecnicoId;
	}
}

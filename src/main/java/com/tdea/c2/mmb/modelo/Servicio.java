package com.tdea.c2.mmb.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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
	
	@NotNull(message = "cliente es requerido")
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "cliente_id", nullable = false, referencedColumnName = "numDocumento")
	private Usuario cliente;
	
	@NotNull(message = "tecnico es requerido")
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "tecnico_id", nullable = false, referencedColumnName = "numDocumento")
	private Tecnico tecnico;
	
	// Constructor vacío (requerido por JPA)
	public Servicio() {
	}
	
	public Servicio(Integer idServicio, LocalDate fechaServicio, String horaServicio, String tipoServicio,
			String estadoServicio, Usuario cliente, Tecnico tecnico) {
		this.idServicio = idServicio;
		this.fechaServicio = fechaServicio;
		this.horaServicio = horaServicio;
		this.tipoServicio = tipoServicio;
		this.estadoServicio = estadoServicio;
		this.cliente = cliente;
		this.tecnico = tecnico;
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
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public Tecnico getTecnico() {
		return tecnico;
	}
	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
}

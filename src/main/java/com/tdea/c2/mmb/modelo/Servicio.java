package com.tdea.c2.mmb.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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
	
	@ManyToOne
	@JoinColumn(name = "serial", nullable = false)
	private Equipo equipo;

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "num_documento", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "tecnico_doc", referencedColumnName = "num_documento", nullable = false)
	private Tecnico tecnico;
	
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
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Tecnico getTecnico() {
		return tecnico;
	}
	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
}
package com.tdea.c2.mmb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
@Table(name = "tecnico")
public class Tecnico {
	
	@Id
	@Column(name = "num_documento")
	private Integer numDocumento;
	
	private String tipoDocumento;
	private String nombreCompleto;
	private Long numCel;
	private String especialidad;
	private String estadoTecnico;
	
	public Tecnico() {
		
	}
	
	// Allow Jackson to deserialize a numeric tecnico reference (e.g. "tecnico": 11111111)
	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public Tecnico(Integer numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	public Tecnico(String tipoDocumento, Integer numDocumento, String nombreCompleto, Long numCel, String especialidad, 
			String estadoTecnico) {
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.nombreCompleto = nombreCompleto;
		this.numCel = numCel;
		this.especialidad = especialidad;
		this.estadoTecnico = estadoTecnico;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(Integer numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Long getNumCel() {
		return numCel;
	}
	public void setNumCel(Long numCel) {
		this.numCel = numCel;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getEstadoTecnico() {
		return estadoTecnico;
	}
	public void setEstadoTecnico(String estadoTecnico) {
		this.estadoTecnico = estadoTecnico;
	}
}

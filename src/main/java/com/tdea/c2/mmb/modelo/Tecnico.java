package com.tdea.c2.mmb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio")

public class Tecnico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private String tipoDocumento;
	private int numDocumento;
	private String nombreCompleto;
	private int numCel;
	private String especialidad;
	private String estadoServicio;
	
	public Tecnico(String tipoDocumento, int numDocumento, String nombreCompleto, int numCel, String especialidad, 
			String estadoServicio) {
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.nombreCompleto = nombreCompleto;
		this.numCel = numCel;
		this.especialidad = especialidad;
		this.estadoServicio = estadoServicio;
	}
	
	
	public String gettipoDocumento() {
		return tipoDocumento;
	}
	public void settipoDocuemtno(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public int getnumDocumento() {
		return numDocumento;
	}
	public void setnumDocumento(int numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getnombreCompleto() {
		return nombreCompleto;
	}
	public void setnombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public int getnumCel() {
		return numCel;
	}
	public void setnumCel(int numCel) {
		this.numCel = numCel;
	}
	public String getespecialidad() {
		return especialidad;
	}
	public void setespecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getestadoServicio() {
		return estadoServicio;
	}
	public void setestadoServicio(String estadoServicio) {
		this.estadoServicio = estadoServicio;
	}
}

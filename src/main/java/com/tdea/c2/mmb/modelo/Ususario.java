package com.tdea.c2.mmb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")

public class Ususario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private String tipoDocumento;
	private int numDocumento;
	private String nombreCompleto;
	private String direccion;
	private String barrio;
	private String ciudad;
	private String correo;
	private int numCel;
	
	public Ususario(String tipoDocumento, int numDocumento, String nombreCompleto, String direccion, String barrio,
			String ciudad, String correo, int numCel) {
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.nombreCompleto = nombreCompleto;
		this.direccion = direccion;
		this.barrio = barrio;
		this.ciudad = ciudad;
		this.correo = correo;
		this.numCel = numCel;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public int getNumDocumento() {
		return numDocumento;
	}
	
	public void setNumDocumento(int numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getBarrio() {
		return barrio;
	}
	
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public int getNumCel() {
		return numCel;
	}
	
	public void setNumCel(int numCel) {
		this.numCel = numCel;
	}
	
	
	
}

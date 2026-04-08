package com.tdea.c2.mmb.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	private Integer numDocumento;
	
	private String tipoDocumento;
	private String nombreCompleto;
	private String direccion;
	private String barrio;
	private String ciudad;
	private String correo;
	private Long numCel;
	
	public Usuario() {
		
	}
	
	public Usuario(String tipoDocumento, Integer numDocumento, String nombreCompleto, String direccion, String barrio,
			String ciudad, String correo, Long numCel) {
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
	
	public Long getNumCel() {
		return numCel;
	}
	
	public void setNumCel(Long numCel) {
		this.numCel = numCel;
	}
	
}

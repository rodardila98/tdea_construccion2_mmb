package com.tdea.c2.mmb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Convierte la clase en tabla
@Table(name = "equipo") // Nombre de la tabla
public class Equipo {
	
	@Id // Id unico para la tabla PK
	private Long serial;
	
	private String marca;
	private String modelo;
	private String tipo;
	
	// Constructor vacio necesario para JPA
	public Equipo() {
		
	}
	
	// Constructor con parametros
	public Equipo(Long  serial, String marca, String modelo, String tipo) {
		this.serial = serial;
		this.marca = marca;
		this.modelo = modelo;
		this.tipo = tipo;
	}
	
	public Long getSerial() {
		return serial;
	}
	
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

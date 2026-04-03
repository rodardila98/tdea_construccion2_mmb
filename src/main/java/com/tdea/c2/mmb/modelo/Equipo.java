package com.tdea.c2.mmb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //convierte la clase en tabla
@Table(name = "equipo") //nombre de la tabla

public class Equipo {
	@Id //id unico para la tabla PK
	//genera el id automaticamente de manera incremental
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private String serial;
	private String marca;
	private String modelo;
	private String tipo;
	
	public Equipo(String marca, String modelo, String tipo) {
		this.marca = marca;
		this.modelo = modelo;
		this.tipo = tipo;
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

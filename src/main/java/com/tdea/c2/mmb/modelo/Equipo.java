package com.tdea.c2.mmb.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity // Convierte la clase en tabla
@Table(name = "equipo") // Nombre de la tabla
public class Equipo {
	
	@Id // Id unico para la tabla PK
	private Long serial;
	
	private String marca;
	private String modelo;
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "num_documento")
	private Usuario usuario; // Relacion con Usuario, un equipo tiene un usuario asignado
	
	// Constructor vacio necesario para JPA
	public Equipo() {
		
	}
    
	// Permitir que Jackson construya un Equipo cuando en el JSON venga solo el id
	// e.g. "equipo": "12345" o "equipo": 12345
	@com.fasterxml.jackson.annotation.JsonCreator
	public Equipo(Object id) {
		if (id == null) return;
		if (id instanceof Number) {
			this.serial = ((Number) id).longValue();
		} else {
			try {
				this.serial = Long.valueOf(id.toString());
			} catch (NumberFormatException e) {
				// dejar serial nulo si no se puede convertir
			}
		}
	}
	
	// Constructor con parametros
	public Equipo(Long  serial, String marca, String modelo, String tipo, Usuario usuario) {
		this.serial = serial;
		this.marca = marca;
		this.modelo = modelo;
		this.tipo = tipo;
		this.usuario = usuario;
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
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
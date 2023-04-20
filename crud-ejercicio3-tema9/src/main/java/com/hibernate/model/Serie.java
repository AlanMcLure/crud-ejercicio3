package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="serie")
public class Serie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idserie")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="temporadas")
	private int numTemporadas;
	
	@Column(name="capitulos")
	private int numCapitulos;
	
	public Serie () {
		super();
	}
	
	public Serie (String nombre, int numTemporadas, int numCapitulos) {
		super();
		this.nombre = nombre;
		this.numTemporadas = numTemporadas;
		this.numCapitulos = numCapitulos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumTemporadas() {
		return numTemporadas;
	}

	public void setNumTemporadas(int numTemporadas) {
		this.numTemporadas = numTemporadas;
	}
	
	public int getNumCapitulos() {
		return numCapitulos;
	}

	public void setNumCapitulos(int numCapitulos) {
		this.numCapitulos = numCapitulos;
	}
}

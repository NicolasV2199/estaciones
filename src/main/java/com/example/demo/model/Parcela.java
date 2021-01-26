package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Parcelas")
public class Parcela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(nullable = false)
	private float latitud;
	
	@Column(nullable = false)
	private float longitud;
	
	@Column(nullable = false)
	private float area;
	
	@Column
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="idmuestreo")
	@JsonIgnoreProperties("parcelas")
	private Muestreo muestreo;

	public Parcela(long id, float latitud, float longitud, float area, String descripcion, Muestreo muestreo) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.area = area;
		this.descripcion = descripcion;
		this.muestreo = muestreo;
	}

	public Parcela() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Muestreo getMuestreo() {
		return muestreo;
	}

	public void setMuestreo(Muestreo muestreo) {
		this.muestreo = muestreo;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	
}


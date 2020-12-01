package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Muestreos")
@JsonIgnoreProperties({"estacion","parcelas"})

public class Muestreo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column
	private char tipo;
	
	@Column
	private float area;
	
	//Relacion Muestreo - Estacion
	@OneToOne
	@JoinColumn(name="estacion_id", referencedColumnName = "id")
	private Estacion estacion;
	
	//Relacion Muestreo - Parcela
	@OneToMany(mappedBy="muestreo")
	private Set<Parcela>parcelas;	

	public Muestreo(long id, char tipo, float area, Estacion estacion, Set<Parcela> parcelas) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.area = area;
		this.estacion = estacion;
		this.parcelas = parcelas;
	}

	public Muestreo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(Set<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

}


package com.example.demo.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="usuarios")
@JsonIgnoreProperties("estaciones")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(nullable = false)
	@Size(min = ConstantesModel.MIN_LENGTH_STRING_FIELD, max = ConstantesModel.MAX_LENGTH_STRING_FIELD)
	private String primerNombre;
	
	@Column(nullable = false)
	@Size(min = ConstantesModel.MIN_LENGTH_STRING_FIELD, max = ConstantesModel.MAX_LENGTH_STRING_FIELD )
	private String primerApellido;
	
	@Column(nullable = false, unique = true)
	@Size(min = ConstantesModel.MIN_LENGTH_EMAIL, max = ConstantesModel.MAX_LENGTH_STRING_FIELD )
	@Email
	private String email;
	
	@Column(nullable = false)
	@Size(min = ConstantesModel.MIN_LENGTH_PASSWORD, max = ConstantesModel.MAX_LENGTH_PASSWORD)
	private String password;
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Estacion>estaciones; 

	public Usuario() {
		super();
	}


	public Usuario(long id, String primerNombre, String primerApellido, String email, String password,
			Set<Estacion> estaciones) {
		super();
		this.id = id;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.email = email;
		this.password = password;
		this.estaciones = estaciones;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getPrimerNombre() {
		return primerNombre;
	}


	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}


	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) throws ConstraintViolationException{
		this.password = password;
	}


	public Set<Estacion> getEstaciones() {
		return estaciones;
	}


	public void setEstaciones(Set<Estacion> estaciones) {
		this.estaciones = estaciones;
	}
	
	
}

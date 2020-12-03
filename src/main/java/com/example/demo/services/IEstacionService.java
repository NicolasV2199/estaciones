package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Estacion;


public interface IEstacionService {

	Estacion crearEstacion(Estacion e);
	Estacion actualizarEstacion(Estacion e); 
	void eliminarEstacion(Estacion e);
	
	
	List<Estacion> listarTodasEstaciones();
	Estacion buscarEstacionId(Long id);
	Estacion buscarEstacionNombre(String nombre);
}

package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Estacion;


public interface IEstacionService {

	Estacion crearEstacion(Estacion e);
	Estacion actualizarEstacion(Estacion e); 
	void eliminarEstacion(Long e);
	
	
	List<Estacion> listarTodasEstaciones();
	Optional<Estacion> buscarEstacionId(Long id);
	Estacion buscarEstacionNombre(String nombre);
}

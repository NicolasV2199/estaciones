package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Muestreo;

public interface IMuestreoService {

	Muestreo crearMuestreo(Muestreo m);
	Muestreo actualizarMuestreo(Muestreo m); 
	void eliminarMuestreo(Long id);
	
	
	List<Muestreo> listarTodosMuestreos();
	Optional<Muestreo> buscarMuestreoId(Long id);
}

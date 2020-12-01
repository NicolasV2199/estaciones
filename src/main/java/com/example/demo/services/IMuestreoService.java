package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Muestreo;

public interface IMuestreoService {

	Muestreo crearMuestreo(Muestreo m);
	Muestreo actualizarMuestreo(Muestreo m); 
	void eliminarMuestreo(Muestreo m);
	
	
	List<Muestreo> listarTodosMuestreos();
	Muestreo buscarMuestreoId(Long id);
}

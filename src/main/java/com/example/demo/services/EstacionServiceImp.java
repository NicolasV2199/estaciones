package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estacion;
import com.example.demo.repositories.EstacionRepository;

@Service
public class EstacionServiceImp implements IEstacionService {

	@Autowired
	EstacionRepository er;
	
	@Override
	public Estacion crearEstacion(Estacion c) {
		return er.save(c);
	}

	@Override
	public Estacion actualizarEstacion(Estacion c) {
		return er.save(c);
	}

	@Override
	public void eliminarEstacion(Long id) {
		er.deleteById(id);

	}

	@Override
	public List<Estacion> listarTodasEstaciones() {
		return er.findAll();
	}

	@Override
	public Optional<Estacion> buscarEstacionId(Long id) {
		return er.findById(id);
	}

	@Override
	public Estacion buscarEstacionNombre(String nombre) {
		return er.findByNombre(nombre);
	}

}
package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Parcela;


public interface IParcelaService {
	Parcela crearParcela(Parcela p);
	Parcela actualizarParcela(Parcela p); 
	void eliminarParcela(Long p);
	
	
	List<Parcela> listarTodasParcelas();
	Optional<Parcela> buscarParcelaId(Long id);
}
package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Parcela;


public interface IParcelaService {
	Parcela crearParcela(Parcela p);
	Parcela actualizarParcela(Parcela p); 
	void eliminarParcela(Parcela p);
	
	
	List<Parcela> listarTodasParcelas();
	Parcela buscarParcelaId(Long id);
}
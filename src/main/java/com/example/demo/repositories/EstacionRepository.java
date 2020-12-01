package com.example.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Estacion;


public interface EstacionRepository extends JpaRepository<Estacion, Long> {

	Estacion findByNombre(String nombre);
	
}

package com.example.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Muestreo;

public interface MuestreoRepository extends JpaRepository<Muestreo, Long> {

	
}
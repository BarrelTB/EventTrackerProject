package com.skilldistillery.eventtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Strain;

public interface StrainRepository extends JpaRepository<Strain, Integer> {

}

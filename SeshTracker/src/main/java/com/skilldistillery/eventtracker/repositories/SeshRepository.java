package com.skilldistillery.eventtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Sesh;

public interface SeshRepository extends JpaRepository<Sesh, Integer> {

}

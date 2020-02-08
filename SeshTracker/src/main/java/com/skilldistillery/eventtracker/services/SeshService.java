package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Sesh;

public interface SeshService {

	Sesh updateSesh(int seshId, Sesh sesh);



	Sesh seshById(int id);

	List<Sesh> listSessions();


	Sesh createSesh(Sesh sesh);

	boolean deleteSesh(int sid);

}

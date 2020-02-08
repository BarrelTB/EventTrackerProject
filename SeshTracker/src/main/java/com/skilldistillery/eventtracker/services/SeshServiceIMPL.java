package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Sesh;
import com.skilldistillery.eventtracker.repositories.SeshRepository;

@Service
public class SeshServiceIMPL implements SeshService {
	
	@Autowired
	private SeshRepository repo;
	
	@Override
	public List<Sesh> listSessions() {
		return repo.findAll();
	}
	
	@Override
	public Sesh seshById(int id) {
		Optional<Sesh> seshOpt = repo.findById(id);
		if (seshOpt.isPresent()) {
			
			return seshOpt.get();
		} else {
			return null;
		}
	}
	
	@Override
	public Sesh createSesh(Sesh sesh) {
		repo.saveAndFlush(sesh);
		return sesh;
	}
	
	@Override
	public boolean deleteSesh(int sid, int stid) {
		Optional<Sesh> seshOpt = repo.findById(sid);
		Sesh sesh = seshOpt.get();
		
		if (repo.findById(sid).isPresent() && (sesh.getStrain().getId() == stid)) {
			repo.deleteById(sid);
			return true;
		} else {
			return false;
			
		}
	}
	
	@Override
	public Sesh updateSesh(int seshId, Sesh sesh) {
		Optional<Sesh> seshOpt = repo.findById(seshId);

		if (seshOpt.isPresent()) {
			sesh.setId(seshId);
			repo.saveAndFlush(sesh);
		}
		
		return sesh;
	}

}

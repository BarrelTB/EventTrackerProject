package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Sesh;
import com.skilldistillery.eventtracker.entities.Strain;
import com.skilldistillery.eventtracker.repositories.SeshRepository;
import com.skilldistillery.eventtracker.repositories.StrainRepository;

@Service
public class SeshServiceIMPL implements SeshService {
	
	@Autowired
	private SeshRepository repo;
	@Autowired
	private StrainRepository repoStrain;
	
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
		
		Optional<Strain> strainOpt = repoStrain.findById(sesh.getStrain().getId());
		Strain strain = strainOpt.get();
		
		sesh.setStrain(strain);
		
		repo.saveAndFlush(sesh);
		return sesh;
	}
	
	@Override
	public boolean deleteSesh(int sid) {
		Optional<Sesh> seshOpt = repo.findById(sid);
		Sesh sesh = seshOpt.get();
		
		if (repo.findById(sid).isPresent()) {
			repo.deleteById(sid);
			
			
			Optional<Strain> strainOpt = repoStrain.findById(sesh.getStrain().getId());
			Strain strain = strainOpt.get();
			if (strain.getSessions().contains(sesh)) {
				strain.getSessions().remove(sesh);	
			}
			
			return true;
		} else {
			return false;
			
		}
	}
	
	@Override
	public Sesh updateSesh(int seshId, Sesh sesh) {
		
		Optional<Strain> strainOpt = repoStrain.findById(sesh.getStrain().getId());
		Strain strain = strainOpt.get();
		
		sesh.setStrain(strain);
		
		Optional<Sesh> seshOpt = repo.findById(seshId);

		if (seshOpt.isPresent()) {
			sesh.setId(seshId);
			repo.saveAndFlush(sesh);
		}
		
		return sesh;
	}

}

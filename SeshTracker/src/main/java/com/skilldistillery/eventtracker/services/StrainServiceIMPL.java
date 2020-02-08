package com.skilldistillery.eventtracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Sesh;
import com.skilldistillery.eventtracker.entities.Strain;
import com.skilldistillery.eventtracker.repositories.StrainRepository;

@Service
public class StrainServiceIMPL implements StrainService {

	@Autowired
	private StrainRepository repo;
	
	@Override
	public List<Strain> listStrains() {
		return repo.findAll();
	}
	
	@Override
	public Strain strainById(int id) {
		Optional<Strain> strainOpt = repo.findById(id);
		if (strainOpt.isPresent()) {
			
			return strainOpt.get();
		} else {
			return null;
		}
	}
	
	@Override
	public Strain createStrain(Strain strain) {
		
		repo.saveAndFlush(strain);
		return strain;
	}
	
	@Override
	public boolean deleteStrain(int sid) {
		
		if (repo.findById(sid).isPresent()) {
			repo.deleteById(sid);
			return true;
		} else {
			return false;
			
		}
	}
	
	@Override
	public Strain updateStrain(int strainId, Strain strain) {
		Optional<Strain> strainOpt = repo.findById(strainId);

		if (strainOpt.isPresent()) {
			strain.setId(strainId);
			repo.saveAndFlush(strain);
		}
		
		return strain;
	}
}

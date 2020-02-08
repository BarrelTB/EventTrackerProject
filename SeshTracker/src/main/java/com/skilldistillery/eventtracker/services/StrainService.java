package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Strain;

public interface StrainService {

	Strain strainById(int id);

	Strain createStrain(Strain strain);

	Strain updateStrain(int strainId, Strain strain);

	boolean deleteStrain(int sid);

	List<Strain> listStrains();

}

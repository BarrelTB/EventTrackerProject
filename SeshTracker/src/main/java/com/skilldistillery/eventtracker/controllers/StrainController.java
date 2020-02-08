package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Strain;
import com.skilldistillery.eventtracker.services.StrainService;

@RestController
@RequestMapping("api")
public class StrainController {
	
	@Autowired
	private StrainService svc;
	
	@GetMapping("strains")
	public List<Strain> index() {
		return svc.listStrains();
	}
	
	@GetMapping("strains/{id}")
	public Strain show(@PathVariable int id, HttpServletResponse response) {
		Strain strain = svc.strainById(id);
		if (strain == null) {
			response.setStatus(404);
		}
		return strain;
	}
	
	@PostMapping("strains")
	public Strain create(@RequestBody Strain strain, HttpServletRequest request, HttpServletResponse response) {
		try {
			strain = svc.createStrain(strain);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(strain.getId());
			String location = url.toString();
			response.setHeader("Location", location);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}
		return strain;

	}
	
	@PutMapping("strains/{id}")
	public Strain update(@PathVariable int id, @RequestBody Strain strain, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			strain = svc.updateStrain(id, strain);
			response.setStatus(200);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(strain.getId());
			String location = url.toString();
			response.setHeader("Location", location);
			return strain;

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}

	}
	
	@DeleteMapping("strains/{id}")
	public void delete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		try {
			svc.deleteStrain(id);
			response.setStatus(204);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(404);
		}

	}

}

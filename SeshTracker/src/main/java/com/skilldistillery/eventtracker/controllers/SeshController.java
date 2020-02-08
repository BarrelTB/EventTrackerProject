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

import com.skilldistillery.eventtracker.entities.Sesh;
import com.skilldistillery.eventtracker.services.SeshService;

@RestController
@RequestMapping("api")
public class SeshController {
	
	@Autowired
	private SeshService svc;
	
	@GetMapping("sessions")
	public List<Sesh> index() {
		return svc.listSessions();
	}
	
	@GetMapping("sessions/{id}")
	public Sesh show(@PathVariable int id, HttpServletResponse response) {
		Sesh sesh = svc.seshById(id);
		if (sesh == null) {
			response.setStatus(404);
		}
		return sesh;
	}
	
	@PostMapping("sessions")
	public Sesh create(@RequestBody Sesh sesh, HttpServletRequest request, HttpServletResponse response) {
		try {
			sesh = svc.createSesh(sesh);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(sesh.getId());
			String location = url.toString();
			response.setHeader("Location", location);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}
		return sesh;

	}
	
	@PutMapping("sessions/{id}")
	public Sesh update(@PathVariable int id, @RequestBody Sesh sesh, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			sesh = svc.updateSesh(id, sesh);
			response.setStatus(200);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(sesh.getId());
			String location = url.toString();
			response.setHeader("Location", location);
			return sesh;

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}

	}
	
	@DeleteMapping("strains/{stid}/sessions/{sid}")
	public void delete(@PathVariable int stid, @PathVariable int sid, HttpServletRequest request, HttpServletResponse response) {
		try {
			svc.deleteSesh(sid, stid);
			response.setStatus(204);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(404);
		}

	}

}
